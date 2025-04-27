import { Component, OnInit, Inject} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { first } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser: User | null = null;
  userId: number | null = null;
  

  constructor(private route: ActivatedRoute,private dialog: MatDialog, private fb: FormBuilder, private userService: UserService) {}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser(): void {
    this.route.params.subscribe(params => {
      const userId = +params['id'];
      if (userId) {
        this.userService.getUser(userId).subscribe(
          (user: User) => {
            this.currentUser = user;
          },
          (error) => {
            console.error('Error fetching user:', error);
          }
        );
      } else {
        console.error('No user ID found in route parameters');
      }
    });
  }

  onDelete(): void {
    this.route.params.subscribe(params => {
      const userId = +params['id'];
      if (userId) {
        this.userService.deleteUser(userId).subscribe(
          (user: User) => {
            this.currentUser = user;
          },
          (error) => {
            console.error('Error delete user:', error);
          }
        );
      } else {
        console.error('No user ID found in route parameters');
      }
    });
  }

  onUpdate(): void {
    const dialogRef = this.dialog.open(UpdateDialog, {
      width: '400px',
      data: { user: this.currentUser }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Updated user:', result);
      }
    });
  }
}

@Component({
  selector: 'update-dialog',
  template: `
    <h2 mat-dialog-title>Add User Address</h2>
    <form [formGroup]="addressForm" (ngSubmit)="onSave()">

      <mat-form-field appearance="fill">
        <mat-label>Address Line 1</mat-label>
        <input matInput formControlName="address_line1" />
        <mat-error *ngIf="addressForm.get('address_line1')?.hasError('required')">Street is required.</mat-error>
      </mat-form-field>

      <mat-form-field appearance="fill">
        <mat-label>Address Line 2</mat-label>
        <input matInput formControlName="address_line2" />
        <mat-error *ngIf="addressForm.get('address_line2')?.hasError('required')">Street is required.</mat-error>
      </mat-form-field>

      <mat-form-field appearance="fill">
        <mat-label>City</mat-label>
        <input matInput formControlName="city" />
        <mat-error *ngIf="addressForm.get('city')?.hasError('required')">City is required.</mat-error>
      </mat-form-field>

      <mat-form-field appearance="fill">
        <mat-label>State</mat-label>
        <input matInput formControlName="country" />
        <mat-error *ngIf="addressForm.get('country')?.hasError('required')">Country is required.</mat-error>
      </mat-form-field>

      <mat-form-field appearance="fill">
        <mat-label>Zip Code</mat-label>
        <input matInput formControlName="postal_code" />
        <mat-error *ngIf="addressForm.get('postal_code')?.hasError('required')">Zip code is required.</mat-error>
      </mat-form-field>

      <mat-dialog-actions>
        <button mat-button (click)="onCancel()">Cancel</button>
        <button mat-button color="primary" type="submit">Save</button>
      </mat-dialog-actions>
    </form>
  `,
})
export class UpdateDialog {
  addressForm: FormGroup;
  userId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private dialogRef: MatDialogRef<UpdateDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService
  ) {    
    this.route.params.subscribe(params => {
    this.userId = +params['id']; // Convert the userId to a number
  });
    this.addressForm = this.fb.group({
      address_line1: ['', Validators.required],
      address_line2: [''],
      city: [data.address?.city || '', Validators.required],
      country: [data.address?.country || '', Validators.required],
      postal_code: [data.address?.postal_code || '', Validators.required]
    });
  }

  onSave(){
    if (this.addressForm.valid) {
      console.log(this.addressForm.value);
      this.userService.saveAddress(this.data.user.id, this.addressForm.value)
      .pipe(first())
      .subscribe(
        (response) => {
          Swal.fire({
            title: 'Success!',
            text: 'Address saved successfully.',
            icon: 'success',
            confirmButtonText: 'OK'
          });
          // Optionally, reset the form or navigate away
          this.addressForm.reset();
        },
        (error) => {
          console.error('Error saving address:', error);
          Swal.fire({
            title: 'Error!',
            text: 'There was a problem saving your address.',
            icon: 'error',
            confirmButtonText: 'Try Again'
          });
        }
      );
    } else {
      Swal.fire({
        title: 'Error!',
        text: 'Please fill in all required fields.',
        icon: 'warning',
        confirmButtonText: 'OK'
      });
    }
  }

  onCancel(): void {
    this.dialogRef.close(); // Close the dialog without saving
  }
}



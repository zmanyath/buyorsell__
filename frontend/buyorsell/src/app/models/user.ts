export class User {
    constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public email: string,
    public phoneNumber: number,
    public password: string,
    public createdAt: Date,
    public modifiedAt: Date,
    ){}


}
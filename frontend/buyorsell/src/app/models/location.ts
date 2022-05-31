export class Location {
    id: number;
    location_line1: string;
    location_line2: string;
    city: string;
    postal_code: number;
    country: string;

    constructor(id: number, location_line1: string, location_line2: string, city: string, postal_code: number, country: string) {
        this.id = id;
        this.location_line1 = location_line1;
        this.location_line2 = location_line2;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
    }
}
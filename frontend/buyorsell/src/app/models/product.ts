export class Product {
    constructor (
        public id: number,
        public name: string,
        public price: number,
        public product_image: string,
        public description: string,
        public sku: number,
        public modifiedAt: Date,
        public createdAt: Date,

    ) {}
}

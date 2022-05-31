CREATE DATABASE classifieds
;
GO

USE classifieds;
GO

CREATE TABLE [user] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [first_name] nvarchar(255),
  [last_name] nvarchar(255),
  [email] nvarchar(255) UNIQUE NOT NULL,
  [phone_number] int,
  [password] text,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime
)
GO

CREATE TABLE [location] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [user_id] int,
  [address_line1] nvarchar(255),
  [address_line2] nvarchar(255),
  [city] nvarchar(255),
  [postal_code] int,
  [country] nvarchar(255)
)
GO

CREATE TABLE [payment] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [user_id] int,
  [payment_type] nvarchar(255),
  [provider] nvarchar(255),
  [account_no] int UNIQUE,
  [expiry] int,
  [cvc] int UNIQUE
)
GO

CREATE TABLE [product] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(255),
  [SKU] int UNIQUE,
  [category_id] int,
  [inventory_id] int,
  [price] decimal NOT NULL,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime,
  [deleted_at] datetime
)
GO

CREATE TABLE [product_category] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [name] nvarchar(255),
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime,
  [deleted_at] datetime
)
GO

CREATE TABLE [order_details] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [user_id] int,
  [total] decimal NOT NULL,
  [payment_id] int,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime
)
GO

CREATE TABLE [order_items] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [order_id] int,
  [product_id] int,
  [quantity] int,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime
)
GO

CREATE TABLE [payment_details] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [order_id] int,
  [amount] decimal NOT NULL,
  [provider] nvarchar(255),
  [status] nvarchar(255),
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime
)
GO

CREATE TABLE [cart_item] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [product_id] int,
  [quantity] int,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime
)
GO

CREATE TABLE [product_inventory] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [quantity] int,
  [created_at] datetime DEFAULT CURRENT_TIMESTAMP,
  [modified_at] datetime,
  [deleted_at] datetime
)
GO

ALTER TABLE [location] ADD FOREIGN KEY ([user_id]) REFERENCES [user] ([id])
GO

ALTER TABLE [payment] ADD FOREIGN KEY ([user_id]) REFERENCES [user] ([id])
GO

ALTER TABLE [product] ADD FOREIGN KEY ([category_id]) REFERENCES [product_category] ([id])
GO

ALTER TABLE [product] ADD FOREIGN KEY ([inventory_id]) REFERENCES [product_inventory] ([id])
GO

ALTER TABLE [order_details] ADD FOREIGN KEY ([user_id]) REFERENCES [user] ([id])
GO

ALTER TABLE [order_details] ADD FOREIGN KEY ([payment_id]) REFERENCES [payment_details] ([id])
GO

ALTER TABLE [order_items] ADD FOREIGN KEY ([order_id]) REFERENCES [order_details] ([id])
GO

ALTER TABLE [order_items] ADD FOREIGN KEY ([product_id]) REFERENCES [product] ([id])
GO

ALTER TABLE [payment_details] ADD FOREIGN KEY ([order_id]) REFERENCES [order_details] ([id])
GO

ALTER TABLE [cart_item] ADD FOREIGN KEY ([product_id]) REFERENCES [product] ([id])
GO

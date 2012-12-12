create table PR_PRProduct (
	productId LONG not null primary key,
	productName VARCHAR(75) null,
	serialNumber VARCHAR(75) null,
	companyId LONG,
	groupId LONG
);
# Procurement Services

You have been tasked by your company's Purchasing Department to create an online procurement process. In this process, users will be able to create purchase orders to be sent your vendors. 

Purchase orders must be created, and approved before they can be sent to the vendor for processing. Once sent to the vendor, the vendor will send an invoice number along with the requested items.

## What will you create?

For this assignment, you are creating the backend of your application. This includes the business logic (services) as well as the RESTful Web Service. The data models below detail the type of data stored. You will create a Firebase database to store the information. 

## Data Models

### User

|field|data type|required|default value|
|---|---|---|---|
|employeeId|String|yes||
|firstName|String|yes||
|lastName|String|yes||
|phoneNumber|String|no||
|approver|Boolean|yes|false|
|active|Boolean|yes|true|

**employeeId is a 10 digit number and may contain leading zeros.*

**Not all users have the ability to approve purchase orders. And a purchase order should never be created and approved by the same user.*

### Vendor

|field|data type|required|default value|possible values|
|---|---|---|---|---|
|vendorId|String|yes| | |
|company|String|yes| | |
|street|Array|yes| | |
|city|String|yes| | |
|state|String|yes| | |
|postalCode|String|no|00000||
|country|String|yes| ||
|contact|String|no| ||
|phoneNumber|String|yes| | |
|emailAddress|String|no| | |
|paymentTerms|String|yes|NET30| NET30, NET60, NET90, Due On Receipt|

**use the country ISO 2 standard*

### Products

|field|data type|required|default value|possible values|
|---|---|---|---|---|
|productId|String|yes| ||
|sku|Number|yes| ||
|name|String|yes| ||
|description|String|no| ||
|unit|String|yes| |Case, Box, Each|
|unitPrice|Number|yes| ||
|vendor|Reference|yes| ||

### Purchase Order

|field|data type|required|default value|possible values|
|---|---|---|---|---|
|poNumber|Number|yes| | |
|poDate|Date|yes| | |
|promisedDate|Date|yes| | |
|shipTo|Map|yes| | |
|vendor|Reference|yes| | |
|items|Array|yes| | |
|shippingMethod|String|yes|FOB|FOB,|
|freightCost|Number|yes| ||
|taxRate|Number|yes| | |
|discountRate|Number|yes| | |
|specialInstructions|String|no| | |
|invoiceNumber|Number|no| | |
|status|String|yes| |New,Pending Approval,Approved,Denied,Sent,Cancelled,Closed|
|createdBy|Reference|yes| | |
|approvedBy|Reference|yes| | |
|createdAt|Datetime|yes| | |
|updatedAt|Datetime|yes| | |

**shipTo follows the same address format from the Vendor model (company,street,city,state,postalCode,country,phoneNumber,emailAddress)*

#### items
This section details what data is found in the item Map

|field|data type|required|default value|
|---|---|---|---|
|sku|String|yes||
|productName|String|yes||
|quantity|Number|yes|1|
|unitPrice|Number|yes||
|unit|String|yes||

## User Stories

### Required Stories

- Create your Firebase database with sufficient test data
- Create the following endpoints
  - `GET /vendor` : Retrieve all vendors
  - `POST /vendor` : Create a new vendor
  - `GET /product` : Retrieve all products
  - `POST /product` : Create a new product
  - `GET /purchaseOrder/{user_id}` : Retrieve all purchase orders created by a specific user
  - `GET /purchaseOrder/{user_id}/pending` : Retrieve all purchase orders for a user based on status pending, list purchase orders that the user is allowed to approve.
  - `POST /purchaseOrder` : Create a new purchase order
  - `PUT /purchaseOrder/{po_number}` : Update a purchase order. You should be able to update 1 or more fields at a time.
  - `GET /purchaseOrder/{po_number}` : Retrieve a purchase order by the purchase order number
- Create your API documentation
    - Export your YAML file and include in you git submission
    - API documentation should include examples for all calls and models
    
**Note: When you retrieve a purchase order, it should include the calculated values for subtotal, discount, tax, and total. These are not stored in the database, but need to be calculated and returned in the response.**

### Stretch Stories
Create other endpoints that you think you would need to build a website.

# License
[License](LICENSE)
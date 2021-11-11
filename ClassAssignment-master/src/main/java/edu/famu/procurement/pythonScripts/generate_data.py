import names
import random as r
from faker import Faker
import string

def create_users(number_of_users):

    """Creates a list of users"""
    users = []
    approver_choices =[True, False]
    for i in range(number_of_users):
        user = {}
        user["employeeId"] = ""
        user["firstName"] = names.get_first_name()
        user["lastName"] = names.get_first_name()
        user["phoneNumber"] = random_phone_num_generator()
        user["approver"] = approver_choices[r.randint(0,len(approver_choices)-1)]
        user["active"] = True

        users.append(user)

    return users



def create_vendors(number_of_vendors):
    """Creates a list of vendors"""

    vendors = []
    payment_terms = ["NET30", "NET60", "NET90", "Due On Receipt"]

    fake = Faker()

    for i in range(number_of_vendors):
        vendor = {}
        n = r.randint(0,3) # random int in range of list

        vendor["vendorId"] = ""
        vendor["company"] = fake.company()
        vendor["street"] = str(fake.street_address()).split()
        vendor["city"] = fake.city()
        vendor["state"] = fake.state()
        vendor["postalCode"] = fake.zipcode()
        vendor["country"] = "US"
        vendor["contact"] = names.get_full_name()
        vendor["phoneNumer"] = random_phone_num_generator()
        vendor["emailAddress"] = random_char(8)+"@gmail.com"
        vendor["paymentTerms"] = payment_terms[n]
        vendors.append(vendor)

    return vendors



def random_char(char_num):
    return ''.join(r.choice(string.ascii_letters) for _ in range(char_num))


def random_phone_num_generator():
    first = str(r.randint(100, 999))
    second = str(r.randint(1, 888)).zfill(3)
    last = (str(r.randint(1, 9998)).zfill(4))
    while last in ['1111', '2222', '3333', '4444', '5555', '6666', '7777', '8888']:
        last = (str(r.randint(1, 9998)).zfill(4))
    return '{}-{}-{}'.format(first, second, last)

def generate_products(number_of_products):
    vendors = ["2GG7EgiTd13W8Fjap2G5","J6V09xINnPyUd4KMJirU", "JxrMpYCqEAArLDfD0bRY","bES287JSnWeKgZ3wpvbE","jEl8S6kY4XbIZb86Xgah","oNVwLIHD9crJlcbISKqW"]
    products = []
    items = ["toothbrush","apples","bubblegum","Lays Chips","Tuna","Bacon"]
    unit = ["Case", "Box", "Each"]
    for i in range(number_of_products):
        product = {}
        product["productId"] = ""
        product["sku"] = r.randint(1111,9999)
        product["name"] = items[r.randint(0,len(items)-1)]
        product["description"] = ""
        product["unit"] = unit[r.randint(0,len(unit)-1)]
        product["unitPrice"] = round(r.uniform(10.5, 75.5),2)
        product["productVendor"] = "Vendor/"+ vendors[r.randint(0,len(vendors)-1)]

        products.append(product)

    return products


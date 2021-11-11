import requests as r
from generate_data import create_vendors,create_users,generate_products
import json


if __name__ == '__main__':
    """Change scripts to automate data insertion """
    vendors = create_vendors(10)
    users = create_users(1)
    products = generate_products(7)


    # response = input("Would you like to create more products: ")
    # if response[0].lower() == 'y':
    #     p_file = open("/Users/amanimuller/Desktop/COP_3060/CA5/src/main/resources/products.json","a")
    #     for product in products:
    #         json_object = json.dumps(product, indent = 4)
    #         p_file.write(json_object +",")
    #
    #     p_file.close()
    #
    #
    #
    # response =  input("Would you like to create more vendors: ")
    # if response[0].lower() == 'y':
    #     v_file = open("/Users/amanimuller/Desktop/COP_3060/CA5/src/main/resources/vendorsText.json","a")
    #     for vendor in vendors:
    #         json_object = json.dumps(vendor,indent=4)
    #         v_file.write(json_object +",")
    #
    #
    # product_url = "http://localhost:8080/api/product"
    # vendor_url = "http://localhost:8080/api/vendor"
    # f = open("/Users/amanimuller/Desktop/COP_3060/CA5/src/main/resources/products.json","r")
    # data  = json.load(f)
    # print(data)
    #need to fix automation of data

    user_url = "http://localhost:8080/api/user"
    for user in users:
        json_object = json.dumps(user)
        res = r.post(user_url,json_object)
        print(res.text)






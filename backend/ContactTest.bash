#curl -X GET --location "http://127.0.0.1:8888/contact" \
#    -H "Accept: application/json"

#curl -X POST --location "http://127.0.0.1:8888/contact" \
#    -H "Content-Type: application/json" \
#    -d "{
#          \"id\": 22,
#          \"name\": \"Jane\",
#          \"address\": \"Smith\",
#          \"number\": \"Smith\",
#          \"email\": \"jane.smith@company.com\"
#        }"

#curl -X POST --location "http://127.0.0.1:8888/customer" \
#    -H "Content-Type: application/json" \
#    -d "{
#          \"id\": \"200\",
#          \"firstName\": \"John\",
#          \"lastName\": \"Smith\",
#          \"email\": \"john.smith@company.com\"
#        }"

curl -X DELETE --location "http://127.0.0.1:8888/contact/1"
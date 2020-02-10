# aws-lambda

Includes using KMS to obtain the db table name to be used. Dt encrypted locally vis the cli. Remember that th ciphertext is base64-encoded and needs to be decoded before decrypted

aws kms encrypt --key-id 02fd7fb6-6992-4ebc-9fe7-0ad4ee095acd --plaintext "IntCompanyTable" --query CiphertextBlob --output text
#!/bin/bash
VAULT_RETRIES=6
echo "Vault client is starting..."
until vault status > /dev/null 2>&1 || [ $VAULT_RETRIES -eq 0 ]; do
echo "Trying to connect to vault server...$((VAULT_RETRIES=VAULT_RETRIES-1))"
sleep 1
done
echo "Authenticating to vault server..."
vault login token=vault-plaintext-root-token
echo "Adding secrets entries..."
vault kv put secret/spring-vault/dev spring.datasource.username=user spring.datasource.password=user
echo "Operation Complete..."
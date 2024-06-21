#!/bin/bash

echo "setup environment"

ENV_FILE="./environments/environment.ts"

ENV_CONTENT="export const environment = {
  production: false,
  apiUrl: '${API_URL}'
};"
echo "Apply variable [${API_URL}]"

echo "$ENV_CONTENT" > "$ENV_FILE"

set -o allexport
source "./environments/environment.ts"
set +o allexport

echo "run app"

ng serve --host 0.0.0.0

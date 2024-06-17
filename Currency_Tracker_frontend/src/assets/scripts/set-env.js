const {writeFileSync} = require('fs');
const {resolve} = require('path');

const targetPath = resolve(__dirname, '../../../environments/environment.ts');
const envConfigFile = `export const environment = {
  production: false,
  apiUrl: '${process.env.API_URL}'
};
`;

writeFileSync(targetPath, envConfigFile, 'utf8');

const targetPathProd = resolve(__dirname, '../../../environments/environment.prod.ts');
const envConfigFileProd = `export const environment = {
  production: true,
  apiUrl: '${process.env.API_URL}'
};
`;

writeFileSync(targetPathProd, envConfigFileProd, 'utf8');

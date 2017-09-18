# Circleci configuration

This is a circleci file configuration with version 2.0 with Android configuration and is created with a workflows to create a parallel runs for test and deploy task

Add this this environment variables on project settings on circleci.com:

- ALIAS
- BUILD_TOOL
- ENCRYPTED_KEY
- GH_EMAIL
- GH_TOKEN
- GH_USER
- KEYSTORE
- TELEGRAM_WEBHOOKS	
- TRANSIFEX_API_TOKEN
- TRANSIFEX_USER

#### Jobs:
A run is comprised of one or more named jobs. Jobs are specified in the jobs map, The name of the job is the key in the map, and the value is a map describing the job.

#### build:
Each job consists of the job’s name as a key and a map as a value. A name should be unique within a current jobs

#### steps:
A list of steps to be performed


#### More information:
- https://circleci.com/docs/2.0/language-android
- https://circleci.com/docs/2.0/

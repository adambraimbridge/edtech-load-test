# Editorial Technology Load Tests

[!image](http://gatling.io/images/gatling-logo.png)

## Why?
We need load tests for our products and services. This aims to provide.

## Pre-Requisites
Git, Java, Maven. Everything else should download the first time you run the test, thanks to Maven.

## How?
- Create an `.env` file in the project's home directory
- Populate the `.env` file (see below)
- Login to http://lantern.ft.com/ get your session ID from cookies
- Place session ID into `src/test/scala/utils/ArticleValues.scala`
- Modify `RunLanternAccessSimulation.sh` if you need to change how many users login, ramp-up time, etc 
- Run `sh RunLanternAccessSimulation.sh`
- Reports can be found in `target/gatling/results`

### Env File Population
- `ET_HOME_CHANNEL` - the Slack channel to alert. This should not change often
- `ET_SESSION_ID` - login to http://lantern.ft.com/ get your session ID from cookies. This may need to be changed regularly

## Todo
- Celebrate
- Web socket for realtime articles
- Post request to historical
- Automated pull-top-article for realtime

## Done
- Ramp-up time functionality
- Send Slack notification on lantern assault
- Change minutes to seconds for ramp up
- Hear response from Gatling: what webpage am I on?
- Add in search, historical, real-time, and section views
- Create .env file for dealing with variables

# Editorial Technology Load Tests

## Why?
We need load tests for our products and services. This aims to provide.

## Pre-requisites
Git, Java, Maven. Everything else should download the first time you run the test, thanks to Maven.

## How?
- Login to http://lantern.ft.com/ get your session ID from cookies
- Place session ID into `src/test/scala/utils/ArticleValues.scala`
- Modify `RunLanternAccessSimulation.sh` if you need to change how many users login, ramp-up time, etc 
- Run `sh RunLanternAccessSimulation.sh`

## Todo
- Hear response from Gatling: what webpage am I on?
- Add in search, historical, real-time, and section views
- Celebrate

## Done
- Ramp-up time functionality
- Send Slack notification on lantern assault
- Change minutes to seconds for ramp up
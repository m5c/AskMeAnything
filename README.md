# Ask Me Anything

A little service for single blinded discussions.

## About

This software allows collectiong anonymous questions of a group while preserving the author's anonymity.

## Usage

 * Run with: ```mvn clean package spring-boot:run```
 * Advise participants to submit questions as POST text/plain question-in-payload to /ama/questions.  
```bash
curl -X POST http://127.0.0.1:8080/ama/questions -H "Cont-Type: text/plain; charset=utf-8" -d "What's the answer to lofe, the universe and everything?"
```
 * Look up answers with GET to [http://127.0.0.1:8080/ama/questions](http://127.0.0.1:8080/ama/questions)

## MISC

 * The IP of Posters is blocked for 1 minute after post.
 * The list of questions is only accessible from localhost.

## Author

 * Author: Maximilian Schiedermeier
 * Github: m5c
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)


#!/bin/bash
port=${1:-8080}
accessToken=IRU0S0KFjMNQJ1sFmB_lH1g87pnC11aSnjLdpTTEsdI

curl -H "content-type: application/json" -H "authorization: Bearer ${accessToken}" -d '{"email":"abhishek.rawat@imp.com"}' http://localhost:${port}/profiles
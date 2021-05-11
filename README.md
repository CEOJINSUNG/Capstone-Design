# Capstone-Design
여기는 2021-1 종합설계 프로젝트 4조 방입니다.

# Frontend

Please refer below url to use react native <br>
https://reactnative.dev/docs/environment-setup -> React Native CLI Quickstart

### On Ubuntu (After environment setup)
```
# After Setup
# You should use two terminal tabs

# 1st tab
cd frontend
npx react-native start

# 2nd tab
cd frontend
npx react-native run-android
```

### 사용한 패키지
```
1. react navigation : https://reactnavigation.org/
2. react-native-vector-icons : https://github.com/oblador/react-native-vector-icons
3. react-native-swiper : https://github.com/leecade/react-native-swiper
```


# How to setup web3.js in React Native(React Native에서 web3.js 연동하기)
```
- This is a simple guide to get you started with using the Ethereum Javascript API (web3.js) with the Create React Native App project. This is not an in-depth guide.
```

## Installation guide

1. Install Create React Native App
```
npm install -g create-react-native-app
```

2. Use create-react-native-app to create a project
```
create-react-native-app my-app
```

3. Install node-libs-browser
```
npm install --save node-libs-browser
```

4. Create a file called globals.js on the root of the project and add the following code into it:
```
if (typeof __dirname === 'undefined') global.__dirname = '/'
if (typeof __filename === 'undefined') global.__filename = ''
if (typeof process === 'undefined') {
    global.process = require('process')
} else {
    const bProcess = require('process')
    for (var p in bProcess) {
        if (!(p in process)) {
            process[p] = bProcess[p]
        }
    }
}
global.Buffer = require('buffer').Buffer
global.process = require('process')
global.process.env.NODE_ENV = __DEV__ ? 'development' : 'production'
if (typeof btoa === 'undefined') {
    global.btoa = function (str) {
        return new Buffer(str, 'binary').toString('base64')
    }
}
if (typeof atob === 'undefined') {
    global.atob = function (b64Encoded) {
        return new Buffer(b64Encoded, 'base64').toString('binary')
    }
}
process.browser = false
if (typeof Buffer === 'undefined') global.Buffer = require('buffer').Buffer
global.location = { protocol: 'file:' }
const isDev = typeof __DEV__ === 'boolean' && __DEV__
process.env['NODE_ENV'] = isDev ? 'development' : 'production'
if (typeof localStorage !== 'undefined') {
    localStorage.debug = isDev ? '*' : ''
}
```

5. Import the global.js file into your App.js file
```
import './global';
```

6. Now we can install the web3.js api
```
npm install --save web3
```

7. Plus install following package for using web3 in React Native
```
npm install rn-nodeify
npm install react-native-crypto
npm install react-native-randombytes
npm install @tradle/react-native-http
npm install https-browserify
```
+ Finish
```
import Web3 from "web3"

Using Web3 & See Web3.js API Documentation
```

## Adding - 추가
```
This README.md is made by JinsungKIM(CEOJINSUNG) 
이 블록체인 연동과정은 김진성(CEOJINSUNG)에 의해 작성됨
```

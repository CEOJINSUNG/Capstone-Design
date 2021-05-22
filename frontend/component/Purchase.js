import React, { useEffect } from "react"
import { useState } from "react";
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    ScrollView,
    useColorScheme,
    Image,
    TextInput,
    RefreshControl,
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";
import { web3, buyAccount, sellAccount, privateKey, myContract } from "./config"

export default function Purchase({ navigation }) {
    const [refreshing, setRefreshing] = useState(false);

    const wait = (timeout) => {
        return new Promise(resolve => {
            setTimeout(resolve, timeout);
        });
    }

    const onRefresh = React.useCallback(() => {
        setRefreshing(true);

        wait(1000).then(() => setRefreshing(false));
    }, []);

    const isDarkMode = useColorScheme() === 'dark';
    const [amount, onChangeAmount] = useState(0);
    const [size, onChangeSize] = useState("");
    const [total, setTotal] = useState(0);

    const Tx = require("ethereumjs-tx").Transaction
    const sendmoney = () => {
        if (total > 0) {
            web3.eth.getTransactionCount(buyAccount, (err, txCount) => {
                const txObject = {
                    nonce: web3.utils.toHex(txCount),
                    to: sellAccount,
                    value: web3.utils.toHex(web3.utils.toWei('0.005', 'ether')),
                    gasLimit: web3.utils.toHex(100000),
                    gasPrice: web3.utils.toHex(web3.utils.toWei('6', 'gwei')),
                }
                //여기서 web3가 2이상이면 아래의 {chain: 'ropsten}을 선언해줘야함
                const tx = new Tx(txObject, { chain: 'ropsten' });
                tx.sign(privateKey);
                const serializedTx = tx.serialize();
                const raw = '0x' + serializedTx.toString('hex');
                web3.eth.sendSignedTransaction(raw)
                    .once('transactionHash', (hash) => {
                        console.info('transactionHash', 'https://ropsten.etherscan.io/tx/' + hash);
                        navigation.navigate("Main")
                    })
                    .once('receipt', (receipt) => {
                        console.info('receipt', receipt);
                    }).on('error', console.error);
            });
        }
    }

    const NFT = () => {
        let NFTDATA = myContract.methods.createCollectible({
            "name": "토트넘",
            "description": "축구 클럽",
            "image" : "https://image.fmkorea.com/files/attach/new/20200302/486616/790531407/2775745592/bbed94098d4bc272ff2cf7bfa4ed9ff4.png",
            "attributes": []
        })
        console.log(NFTDATA)
        web3.eth.getTransactionCount(buyAccount, (err, txCount) => {
            const txObject = {
                nonce: web3.utils.toHex(txCount),
                to: sellAccount,
                value: 0x0,
                gasLimit: web3.utils.toHex(100000),
                gasPrice: web3.utils.toHex(web3.utils.toWei('6', 'gwei')),
                data: NFTDATA.encodeABI()
            }
            //여기서 web3가 2이상이면 아래의 {chain: 'ropsten}을 선언해줘야함
            const tx = new Tx(txObject, { chain: 'ropsten' });
            tx.sign(privateKey);
            const serializedTx = tx.serialize();
            const raw = '0x' + serializedTx.toString('hex');
            web3.eth.sendSignedTransaction(raw)
                .once('transactionHash', (hash) => {
                    console.info('transactionHash', 'https://ropsten.etherscan.io/tx/' + hash);
                })
                .once('receipt', (receipt) => {
                    console.info('receipt', receipt);
                }).on('error', console.error);
        });
    }

    useEffect(() => {
        web3.eth.getBalance(buyAccount, function (err, wei) {
            const balance = web3.utils.fromWei(wei, 'ether')
            console.log(balance)
        })

        setTotal(amount * 0.005)
    }, [refreshing, amount])
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic"
                refreshControl={
                    <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
                }
            >
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    marginTop: 20,
                    marginLeft: "5%",
                }}>
                    <TouchableOpacity onPress={() => navigation.goBack()}>
                        <Image source={require("./icon/arrow.png")} style={{
                            width: 14,
                            height: 14,
                        }} />
                    </TouchableOpacity>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        marginLeft: 8,
                        color: "#000000"
                    }}>구매하기</Text>
                </View>
                <Image 
                    source={require("./icon/post.png")}
                    resizeMode="stretch"
                    style={{
                        marginTop: 20,
                        width: "100%"
                    }}
                />
                <Text style={{
                    fontSize: 16,
                    color: "#000000",
                    marginTop: 16,
                    marginLeft: "5%"
                }}>주니어 축구 저지</Text>
                <Text style={{
                    fontSize: 20,
                    color: "#000000",
                    marginLeft: "5%",
                    fontWeight: "bold",
                    marginTop: 2,
                }}>토트넘 홋스퍼 2020/21 스타디움 홈</Text>
                <Text style={{
                    fontSize: 20,
                    color: "#000000",
                    marginLeft: "5%",
                    fontWeight: "bold",
                    marginTop: 16,
                }}>50% Sale</Text>
                <Text style={{
                    marginTop: 4,
                    marginLeft: "5%"
                }}>
                    <Text style={{
                        fontSize: 24,
                        fontWeight: "bold",
                        color: "#650ab2",
                        marginRight: 16,
                    }}>0.005 ether </Text>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#000000",
                        textDecorationLine: "line-through"
                    }}>0.01 ether</Text>
                </Text>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    marginLeft: "5%",
                    marginTop: 12,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000",
                        width: 80,
                        textAlign: "left"
                    }}>수량</Text>
                    <TextInput 
                        value={amount}
                        onChangeText={onChangeAmount}
                        placeholder="주문할 수량을 입력해주세요"
                        style={{
                            width: 200,
                            borderBottomColor: "rgba(0, 0, 0, 0.16)",
                            borderBottomWidth: 1,
                            color: "#000000"
                        }}
                    />
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    marginLeft: "5%",
                    marginTop: 4,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000",
                        width: 80,
                        textAlign: "left"
                    }}>사이즈</Text>
                    <TextInput 
                        value={size}
                        onChangeText={onChangeSize}
                        placeholder="사이즈를 입력해주세요"
                        style={{
                            width: 200,
                            borderBottomColor: "rgba(0, 0, 0, 0.16)",
                            borderBottomWidth: 1,
                            color: "#000000"
                        }}
                    />
                </View>
                <Text style={{
                    fontSize: 16,
                    fontWeight: "bold",
                    color: "#000000",
                    marginTop: 16,
                    alignSelf: "flex-end",
                    marginRight: "35%"
                }}>총 금액</Text>
                <Text style={{
                    fontSize: 24,
                    fontWeight: "bold",
                    marginRight: "10%",
                    alignSelf: "flex-end",
                    color: "#650ab2",
                }}>{total} ether</Text>
                <TouchableOpacity onPress={sendmoney} style={{
                    width: "80%",
                    backgroundColor: "#650ab2",
                    height: 56,
                    borderRadius: 8,
                    alignSelf: "center",
                    marginTop: 20,
                    marginBottom: 40,

                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                }}>
                    <Text style={{
                        fontWeight: "bold",
                        fontSize: 16,
                        color: "#ffffff"
                    }}>구매하기</Text>
                </TouchableOpacity>
            </ScrollView>
        </SafeAreaView>
    )
}
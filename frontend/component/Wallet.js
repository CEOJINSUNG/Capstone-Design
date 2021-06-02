import React, { useEffect, useState } from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    Image,
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";
import QRCode from "react-native-qrcode-svg";
import Entypo from "react-native-vector-icons/Entypo"
import Ionicons from "react-native-vector-icons/Ionicons"
import { web3, buyAccount, sellAccount, privateKey, myContract } from "./config"
import vip from "./icon/vip-card.png"

export default function Wallet({ navigation, route }) {
    const isDarkMode = useColorScheme() === 'dark';

    const { token } = route.params;
    const [hash, setHash] = useState("")

    const Tx = require("ethereumjs-tx").Transaction
    const NFT = async () => {
        let NFTDATA = myContract.methods.createCollectible({
            "name": "토트넘",
            "description": "축구 클럽",
            "image": "https://image.fmkorea.com/files/attach/new/20200302/486616/790531407/2775745592/bbed94098d4bc272ff2cf7bfa4ed9ff4.png",
            "attributes": []
        })
        await web3.eth.getTransactionCount(buyAccount, (err, txCount) => {
            const txObject = {
                nonce: web3.utils.toHex(txCount),
                to: sellAccount,
                value: web3.utils.toHex(web3.utils.toWei('0.005', 'ether')),
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
                    setHash('https://ropsten.etherscan.io/tx/' + hash)
                })
                .once('receipt', (receipt) => {
                    console.info('receipt', receipt);
                    fetch('http://3.139.204.200:8080/user/add/histories', {
                        method: 'POST',
                        credentials: true,
                        headers: {
                            "Accept": "application/json",
                            'Content-Type': 'application/json',
                            'X-AUTH-TOKEN': token
                        },
                        body: JSON.stringify([{
                            imageUrl: "https://image.fmkorea.com/files/attach/new/20200302/486616/790531407/2775745592/bbed94098d4bc272ff2cf7bfa4ed9ff4.png",
                            ehterUrl: hash
                        }])
                    })
                        .then(res => res.json())
                        .then(response => console.log(response))
                        .catch((error) => {
                            console.log(error);
                        });
                }).on('error', console.error);
        });
    }

    const [balance, setBalance] = useState()
    useEffect(() => {
        web3.eth.getBalance(buyAccount, function (err, wei) {
            const a = web3.utils.fromWei(wei, 'ether')
            console.log(a)
            setBalance(a)
        })
    }, [])

    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <View style={{
                backgroundColor: "#650ab2",
                width: "100%",
                height: "50%",
                zIndex: 0,
                position: "absolute",
                top: 0,
            }} />
            <View style={{
                backgroundColor: "#F7F9FE",
                width: "100%",
                height: "50%",
                zIndex: 1,
                position: "absolute",
                top: "50%",
            }} />
            <View style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "center",

                width: "100%",
                marginTop: 20
            }}>
                <View style={{
                    width: "30%",
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "flex-start",
                    justifyContent: "center"
                }}>
                    <TouchableOpacity onPress={() => navigation.goBack()}>
                        <Entypo name="chevron-left" color="#ffffff" size={18} />
                    </TouchableOpacity>
                </View>
                <Text style={{
                    width: "30%",
                    textAlign: "center",
                    fontSize: 16,
                    color: "#ffffff",
                    fontWeight: "bold",
                }}>FanS Wallet</Text>
                <View style={{
                    width: "30%",
                }} />
            </View>
            <View style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                alignSelf: "center",

                width: "90%",
                backgroundColor: "#ffffff",
                padding: "5%",
                minHeight: "65%",
                borderRadius: 16,
                zIndex: 2,
                elevation: 2,
                marginTop: "10%",
            }}>
                <QRCode 
                    value={buyAccount}
                    size={150}
                />
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    width: 250,

                    marginTop: 20,
                }}>
                    <Ionicons name="clipboard-outline" size={15} color="#000000" />
                    <Text style={{
                        fontSize: 10,
                        color: "#000000",
                        marginLeft: 8,
                    }}>{buyAccount}</Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    width: 250,

                    marginTop: 12,
                }}>
                    <Text style={{
                        fontSize: 18,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>김진성</Text>
                    <Text style={{
                        fontSize: 12,
                        marginLeft: 12,
                        color: "#000000"
                    }}>Metamask Wallet Account</Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    width: 250,

                    marginTop: 8,
                }}>
                    <Text style={{
                        fontSize: 14,
                        color: "#000000",
                        width: 100,
                    }}>현재 잔액</Text>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>{balance} ether</Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    width: 250,

                    marginTop: 16,
                }}>
                    <Image source={vip} width={30} />
                    <Text style={{
                        fontSize: 16,
                        color: "#000000",
                        marginLeft: 12
                    }}>
                        <Text>당신은 </Text>
                        <Text style={{
                            color: "#650ab2",
                            fontWeight: "bold"
                        }}>1 등급</Text>
                        <Text> 입니다.</Text>
                    </Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    width: 300,
                    alignSelf: "flex-end",

                    marginTop: 4,
                }}>
                    <Text style={{
                        fontSize: 8,
                        color: "#000000",
                        marginLeft: 62,
                    }}>* 멤버십 별 티어를 보시려면 홈페이지 멤버별 티어를 보시면 됩니다.</Text>
                </View>
            </View>
            <View style={{
                width: 300,
                height: 56,
                borderRadius: 8,
                backgroundColor: "#650ab2",
                zIndex: 2,
                marginTop: "10%",

                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                alignSelf: "center",
            }}>
                <TouchableOpacity onPress={NFT}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#ffffff"
                    }}>후원하기</Text>
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    )
}
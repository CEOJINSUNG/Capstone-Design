import React, { useEffect } from "react"
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
import { web3, buyAccount, sellAccount, privateKey } from "./config"
import vip from "./icon/vip-card.png"

export default function Wallet({ navigation }) {
    const isDarkMode = useColorScheme() === 'dark';

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

    useEffect(() => {
        web3.eth.getBalance(buyAccount, function (err, wei) {
            const balance = web3.utils.fromWei(wei, 'ether')
            console.log(balance)
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

                    marginTop: 12,
                }}>
                    <Text style={{
                        fontSize: 14,
                        color: "#000000",
                        width: 100,
                    }}>총 후원 금액</Text>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>1000000 ether</Text>
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
                    }}>사용한 금액</Text>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>235000 ether</Text>
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
                    }}>765000 ether</Text>
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
                    }}>* 멤버십 별 티어를 보시려면 홈페이지>멤버별 티어를 보시면 됩니다.</Text>
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
                <TouchableOpacity onPress={sendmoney}>
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
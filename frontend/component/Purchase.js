import React from "react"
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
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";

export default function Purchase({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [amount, onChangeAmount] = useState("");
    const [size, onChangeSize] = useState("");
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic">
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
                    fontSize: 24,
                    color: "#650ab2",
                    marginLeft: "5%",
                    fontWeight: "bold",
                    marginTop: 16,
                }}>20% Sale</Text>
                <Text style={{
                    marginTop: 4,
                    marginLeft: "5%"
                }}>
                    <Text style={{
                        fontSize: 20,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>109,000원 -> </Text>
                    <Text style={{
                        fontSize: 20,
                        fontWeight: "bold",
                        color: "#650ab2"
                    }}>87,200원</Text>
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
                }}>87,200원</Text>
                <TouchableOpacity style={{
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
import React from "react"
import { 
    View,
    Text,
    Image,
    ScrollView, 
    SafeAreaView} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";

            
export default function ClubMain({navigation}) {      
    return(
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-start",
                    width: "100%",
                    paddingTop: 20,
                    backgroundColor: "#ffffff",
                }}>
                    <View style={{
                        marginLeft: "5%",
                    }}>
                        <Text style={{
                            fontSize: 24,
                            fontWeight: "bold",
                            color: "#650AB2"
                        }}>Fan</Text>
                        <Text>
                            <Text style={{
                                fontSize: 24,
                                fontWeight: "bold",
                                color: "#650AB2"
                            }}>S</Text>
                            <Text style={{
                                fontSize: 18,
                                color: "#650AB2"
                            }}>ubscribe</Text>
                        </Text>
                    </View>
                    <View style={{
                        display: "flex",
                        flexDirection: "column",
                        marginLeft: 12,
                    }}>
                        <Text style={{
                            fontSize: 15,
                            fontWeight: "bold",
                        }}>
                            <Text style={{
                                color: "#000000"
                            }}>구단을 </Text>
                            <Text style={{
                                color: "#650AB2"
                            }}>후원</Text>
                            <Text style={{
                                color: "#000000"
                            }}>하고 다양한 </Text>
                            <Text style={{
                                color: "#650AB2"
                            }}>혜택</Text>
                            <Text style={{
                                color: "#000000"
                            }}>을 받으세요</Text>
                        </Text>
                        <Text style={{
                            fontSize: 14,
                            color: "#000000",
                            marginTop: 16,
                            fontWeight: "bold",
                        }}>토트넘에서 제공하는 후원 방식</Text>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "flex-start",
                            marginTop: 16,
                        }}>
                            <Image source={require("../icon/cooperate.png")} />
                            <View style={{
                                marginLeft: 8,
                            }}>
                                <Text style={{
                                    fontSize: 14,
                                    color: "#000000",
                                    fontWeight: "bold",
                                }}>1. 일반 후원</Text>
                                <Text style={{
                                    fontSize: 14,
                                    color: "#650AB2",
                                    fontWeight: "bold",
                                    marginTop: 4,
                                    marginLeft: 12,
                                }}>FOLLOW YOUR CLUB!</Text>
                            </View>
                        </View>
                        <View style={{
                            marginTop: 20,
                            marginLeft: 50,
                        }}>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000"
                            }}>
                                <Text>월 </Text>
                                <Text style={{ fontWeight: "bold" }}>5천원</Text>
                            </Text>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000"
                            }}>
                                <Text>티켓 및 유니폼 구매 시 </Text>
                                <Text style={{ fontWeight: "bold" }}>최대 30%</Text>
                                <Text>할인</Text>
                            </Text>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000"
                            }}>
                                <Text>후원 연차에 따른 </Text>
                                <Text style={{ fontWeight: "bold" }}>구단 이벤트 제공</Text>
                            </Text>
                        </View>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "flex-start",
                            marginTop: 20,
                        }}>
                            <Image source={require("../icon/money.png")} />
                            <View style={{
                                marginLeft: 8,
                            }}>
                                <Text style={{
                                    fontSize: 14,
                                    color: "#000000",
                                    fontWeight: "bold",
                                }}>2. 사전 구매식 후원</Text>
                                <Text style={{
                                    fontSize: 14,
                                    color: "#650AB2",
                                    fontWeight: "bold",
                                    marginTop: 4,
                                    marginLeft: 12,
                                }}>BUY AND HOLD!</Text>
                            </View>
                        </View>
                        <View style={{
                            marginTop: 20,
                            marginLeft: 50,
                        }}>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000"
                            }}>쌓아놓은 적립금으로 추후</Text>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000",
                                fontWeight: "bold",
                            }}>티켓 및 유니폼 구매</Text>
                        </View>
                    </View>
                </View>
                <Text style={{
                    fontSize: 14,
                    fontWeight: "bold",
                    color: "#000000",

                    marginTop: 50,
                    alignSelf: "center"
                }}>미리 구매하고 여름에 같이 축구 보러가자!</Text>
                <TouchableOpacity onPress={() => navigation.navigate("Shop")} style={{
                    width: 150,
                    height: 30,
                    backgroundColor: "#650AB2",
                    borderRadius: 8,
                    alignSelf: "center",
                    marginTop: 16,

                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center"
                }}>
                    <Text style={{
                        fontSize: 12,
                        fontWeight: "bold",
                        color: "#ffffff"
                    }}>후원하러 가자</Text>
                </TouchableOpacity>
            </ScrollView>
        </SafeAreaView>
       
    );
}      
                
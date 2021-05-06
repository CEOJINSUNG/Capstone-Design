import React from "react"
import {
    View,
    Text,
    Image,
    StatusBar,
    ScrollView,
    useColorScheme,
    SafeAreaView,
    TouchableWithoutFeedback
} from "react-native"
import MaterialIcons from "react-native-vector-icons/MaterialIcons"
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import { TouchableOpacity } from "react-native-gesture-handler";

const tab = createMaterialTopTabNavigator()

export default function Community({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",

                    width: "90%",
                    alignSelf: "center",
                    marginTop: 20,
                }}>
                    <TouchableWithoutFeedback >
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                        }}>
                            <Text style={{
                                fontSize: 14,
                                color: "#000000",
                                opacity: 0.6,
                                fontWeight: "bold",
                            }}>Tottenham Hotspurs</Text>
                            <MaterialIcons name="arrow-drop-down" size={20} color="rgba(0, 0, 0, 0.6)" />
                        </View>
                    </TouchableWithoutFeedback>
                    <Image source={require("./icon/tottenham.png")} />
                </View>
                <Text style={{
                    marginTop: 12,
                    fontSize: 24,
                    color: "#000000",
                    marginLeft: "5%",
                    fontWeight: "bold"
                }}>Come on Your Spurs!!</Text>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    marginLeft: "5%",
                    marginTop: 16,
                    paddingBottom: 1,
                }}>
                    <View style={{
                        width: 100,
                        height: 80,
                        borderRadius: 16,
                        backgroundColor: "#ffffff",
                        elevation: 1.5,
                    }}>
                        <Text style={{
                            fontSize: 10,
                            fontWeight: "bold",
                            color: "#000000",
                            marginTop: 12,
                            marginLeft: 8,
                        }}>이번 경기 결과</Text>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                            justifyContent: "space-between",
                            alignSelf: "center",
                            marginTop: 8,
                            width: 84,
                        }}>
                            <View style={{
                                display: "flex",
                                flexDirection: "column",
                                alignItems: "center",
                            }}>
                                <Image source={require("./icon/brighten.png")} />
                                <Text style={{
                                    fontSize: 4,
                                    fontWeight: "bold",
                                    color: "#000000",
                                    marginTop: 4,
                                }}>브라이튼</Text>
                            </View>
                            <View style={{
                                display: "flex",
                                flexDirection: "column",
                                alignItems: "center",
                            }}>
                                <Text style={{
                                    fontSize: 8,
                                    color: "#000000"
                                }}>1 - 0</Text>
                                <Text style={{
                                    width: 30,
                                    height: 10,
                                    borderRadius: 12,
                                    backgroundColor: "#FF0000",

                                    fontSize: 6,
                                    fontWeight: "bold",
                                    textAlign: "center",
                                    color: "#ffffff"
                                }}>LOSE</Text>
                            </View>
                            <View style={{
                                display: "flex",
                                flexDirection: "column",
                                alignItems: "center",
                            }}>
                                <Image width={20} height={20} resizeMethod="resize" resizeMode="contain" source={require("./icon/tottenham.png")} />
                                <Text style={{
                                    fontSize: 4,
                                    fontWeight: "bold",
                                    color: "#000000",
                                    marginTop: 4,
                                }}>토트넘</Text>
                            </View>
                        </View>
                    </View>

                    <View style={{
                        width: 100,
                        height: 80,
                        borderRadius: 16,
                        backgroundColor: "#ffffff",
                        elevation: 1.5,
                        marginLeft: 16,
                    }}>
                        <Text style={{
                            fontSize: 10,
                            fontWeight: "bold",
                            color: "#000000",
                            marginTop: 12,
                            marginLeft: 8,
                        }}>[오피셜]</Text>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                            justifyContent: "space-between",
                            alignSelf: "center",
                            marginTop: 8,
                            width: 84,
                        }}>
                            <Image source={require("./icon/gajaniga.png")} />
                            <View>
                                <Text style={{
                                    fontSize: 6,
                                    color: "#000000",
                                    fontWeight: "bold",
                                    marginBottom: 4,
                                }}>파울로 가자니가</Text>
                                <Text style={{
                                    fontSize: 8,
                                    color: "#000000",
                                    fontWeight: "bold",
                                }}>스페인 엘체로</Text>
                                <Text style={{
                                    fontSize: 10,
                                    color: "#650AB2",
                                    fontWeight: "bold",
                                }}>임대이적</Text>
                            </View>
                        </View>
                    </View>

                    <View style={{
                        width: 100,
                        height: 80,
                        borderRadius: 16,
                        backgroundColor: "#ffffff",
                        elevation: 1.5,
                        marginLeft: 16,
                    }}>
                        <Text style={{
                            fontSize: 10,
                            fontWeight: "bold",
                            color: "#000000",
                            marginTop: 12,
                            marginLeft: 8,
                        }}>인기칼럼</Text>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                            justifyContent: "space-between",
                            alignSelf: "center",
                            marginTop: 8,
                            width: 84,
                        }}>
                            <Image source={require("./icon/park.png")} />
                            <View>
                                <Text style={{
                                    fontSize: 8,
                                    color: "#000000",
                                    fontWeight: "bold",
                                    marginBottom: 4,
                                }}>토트넘</Text>
                                <Text style={{
                                    fontSize: 10,
                                    color: "#000000",
                                    fontWeight: "bold",
                                }}>이대로는</Text>
                                <Text style={{
                                    fontSize: 10,
                                    color: "#000000",
                                    fontWeight: "bold",
                                }}>안된다!!</Text>
                            </View>
                        </View>
                    </View>
                </View>
                {/* FanS View */}
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
                            <Image source={require("./icon/cooperate.png")} />
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
                            <Image source={require("./icon/money.png")} />
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
    )
}
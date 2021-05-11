import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    ScrollView,
    useColorScheme,
    Image
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";
import AntDesign from "react-native-vector-icons/AntDesign"
import MaterialIcons from "react-native-vector-icons/MaterialIcons"

export default function Shop({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';

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
                    }}>Shop</Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",
                    alignSelf: "center",

                    width: "90%",
                    marginTop: 24,
                }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>FanS' Shop</Text>
                    <Text style={{
                        fontSize: 10,
                        textDecorationLine: "underline",
                        opacity: 0.4,
                        color: "#000000"
                    }}>더 보러 가기</Text>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-start",
                    justifyContent: "space-between",
                    alignSelf: "center",

                    width: "90%",
                    marginTop: 16,
                }}>
                    <DisplayShop 
                        link={require("./icon/tshirts.png")}
                        title="토트넘 축구 저지"
                        price="0.005 ether"
                        saleprice="0.01 ether"
                        rate={4.5}
                        navigation={navigation}
                    />
                    <DisplayShop 
                        link={require("./icon/ballcap.png")}
                        title="볼캡 화이트"
                        price="0.01 ether"
                        saleprice="0.02 ether"
                        rate={3.8}
                    />
                </View>
                <View style={{
                    width: "100%",
                    height: 100,
                    backgroundColor: "#650ab2",
                    marginTop: 32,

                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}>
                    <View style={{
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "flex-start",
                        marginLeft: "5%",
                    }}>
                        <Text style={{
                            color: "#ffffff",
                            fontSize: 18,
                            fontWeight: "bold",
                            marginBottom: 8,
                        }}>5월 한달 동안</Text>
                        <Text style={{
                            fontSize: 20,
                            fontWeight: "bold",
                            fontWeight: "bold",
                            color: "#ffffff"
                        }}>오직 Fans' Shop에서</Text>
                    </View>
                    <View style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",

                        backgroundColor: "#ffffff",
                        height: 95,
                        width: 100,
                        marginRight: 2.5,
                    }}>
                        <Image 
                            source={require("./icon/character.png")}
                            style={{
                                width: 80,
                            }}
                        />
                    </View>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",
                    alignSelf: "center",

                    width: "90%",
                    marginTop: 24,
                    marginBottom: 16,
                }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>초특가 세일</Text>
                    <Text style={{
                        fontSize: 10,
                        color: "#000000",
                        opacity: 0.4,
                        textDecorationLine: "underline"
                    }}>구매하기</Text>
                </View>
                <MiniDisplay />
                <MiniDisplay />
                <MiniDisplay />
                <MiniDisplay />
                <MiniDisplay />
            </ScrollView>
        </SafeAreaView>
    )
}

const DisplayShop = ({link, title, price, saleprice, rate, navigation}) => {
    return (
        <TouchableOpacity onPress={() => navigation.navigate("Purchase")} style={{
            width: "48%",
            minWidth: 160,

            display: "flex",
            flexDirection: "column",
        }}>
            <Image 
                source={link}
                style={{
                    width: "100%",
                }}
            />
            <View style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "space-between",
                width: "100%",
            }}>
                <View style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "flex-start"
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        marginBottom: 4,
                        color: "#000000"
                    }}>{title}</Text>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "flex-end",
                    }}>
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            color: "#650ab2",
                        }}>{price}</Text>
                        <Text style={{
                            fontSize: 12,
                            color: "#000000",
                            textDecorationLine: "line-through",
                            marginLeft: 4,
                        }}>{saleprice}</Text>
                    </View>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                }}>
                    <AntDesign name="star" color="#FFC107" size={15} />
                    <Text style={{
                        fontSize: 12,
                        fontWeight: "bold",
                        marginLeft: 4,
                    }}>{rate}</Text>
                </View>
            </View>
        </TouchableOpacity>
    )
}

const MiniDisplay = () => {
    return (
        <View style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "space-between",
            alignSelf: "center",

            width: "90%",
            minHeight: 80,
            marginBottom: 16,
        }}>
            <View style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                height: 80,
            }}>
                <Image
                    resizeMode="contain"
                    source={require("./icon/tshirts.png")}
                    style={{
                        width: 80,
                    }}
                />
                <View style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "flex-start",
                    justifyContent: "space-between",

                    height: 80,
                    marginLeft: 12,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000"
                    }}>토트넘 축구 저지</Text>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "flex-end",
                    }}>
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            color: "#650ab2",
                        }}>0.005 ether</Text>
                        <Text style={{
                            fontSize: 10,
                            color: "#000000",
                            textDecorationLine: "line-through",
                            marginLeft: 4,
                        }}>0.01 ether</Text>
                    </View>
                    <Text style={{
                        fontSize: 12,
                        color: "#000000",
                    }}>친환경 소재로 만들어진 축구 저지</Text>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                    }}>
                        <AntDesign name="star" color="#FFC107" size={15} />
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            marginLeft: 4,
                        }}>4.5</Text>
                    </View>
                </View>
            </View>
            <MaterialIcons name="keyboard-arrow-right" size={24} color="#000000" />
        </View>
    )
}
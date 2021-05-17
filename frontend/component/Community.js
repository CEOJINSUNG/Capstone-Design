import React from "react"
import {
    View,
    Text,
    Image,
    StatusBar,
    ScrollView,
    useColorScheme,
    SafeAreaView,
    TouchableWithoutFeedback,
} from "react-native"
import MaterialIcons from "react-native-vector-icons/MaterialIcons"
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import { TouchableOpacity } from "react-native-gesture-handler";

import ClubMain from "./community/ClubMain";
import FreeBoard from "./community/FreeBoard";
import DiscussBoard from "./community/DiscussBoard";

const Tab = createMaterialTopTabNavigator()

export default function Community({token, navigation}) {
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
                <Tab.Navigator>
                    <Tab.Screen name="FanS" component={ClubMain} />
                    <Tab.Screen name="자유게시판" children={() => <FreeBoard token={token} navigation={navigation} />} />
                    <Tab.Screen name="토론게시판" component={DiscussBoard} />
                </Tab.Navigator>
                
            </ScrollView>
        </SafeAreaView>
    )
}
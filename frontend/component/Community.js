import React from "react"
import {
    View,
    Text,
    Image,
    StatusBar,
    ScrollView,
    useColorScheme,
    SafeAreaView,
    StyleSheet,
    TouchableWithoutFeedback
} from "react-native"
import MaterialIcons from "react-native-vector-icons/MaterialIcons"

export default function Community() {
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
                        elevation: 1,
                    }}>
                        <Text style={{
                            fontSize: 10,
                            fontWeight: "bold",
                            color: "#000000",
                            marginTop: 8,
                            marginLeft: 8,
                        }}>이번 경기 결과</Text>
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                            marginTop: 8,
                        }}>

                        </View>
                    </View>
                </View>
            </ScrollView>
        </SafeAreaView>
    )
}
import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    Image
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";
import Entypo from "react-native-vector-icons/Entypo"

export default function Wallet({ navigation }) {
    const isDarkMode = useColorScheme() === 'dark';
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
            }}></View>
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
                <TouchableOpacity>
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
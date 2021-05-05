import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    Image
} from "react-native"

export default function Splash({ navigation }) {
    const isDarkMode = useColorScheme() === 'dark';
    setTimeout(() => {
        navigation.navigate("Main")
    }, 2000)
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <View style={{
                backgroundColor: "#ffffff",
                flex: 1,

                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center"
            }}>
                <Text style={{
                    fontSize: 21,
                    fontWeight: "bold",
                    color: "#000000",
                    marginBottom: 24,
                }}>
                    <Text>팬들을 위한 </Text>
                    <Text style={{
                        color: "#650AB2"
                    }}>클럽 후원</Text>
                    <Text> 플랫폼</Text>
                </Text>
                <Image source={require("./icon/logo.png")}  />
                <Text style={{
                    fontSize: 36,
                    color: "#650AB2",
                    marginTop: 16,
                    fontWeight: "bold",
                }}>Fan:S</Text>
            </View>
        </SafeAreaView>
    )
}
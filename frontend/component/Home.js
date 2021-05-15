import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    TextInput,
    StyleSheet,
    Button,
    Alert,
    Image,
    TouchableOpacity
} from "react-native"

import { NavigationHelpersContext } from "@react-navigation/core";
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

export default function Home({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [email, setEmail] = React.useState("");
    const [password, setPassword] = React.useState("");

    //TODO : backend port should be opened
    async function logInButtonHandler(){
        var data = {
            "email" : email,
            "password" : password
        }
        await fetch('http://3.139.204.200:8080/auth/login', {
            method: 'POST',
            credentials: true,
            headers: {
                "Accept": "application/json",
                'Content-type': 'application/json',
            },
            body: JSON.stringify(data)
        })
        .then(response => response.text())
        .then(response => {
            console.log(response);
            navigation.navigate('Main');
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <View style={{
                backgroundColor: "#ffffff",
                flex: 1,

                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
            }}>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    justifyContent: "center",
                    marginBottom: 0,
                }}>
                    <Image resizeMode="contain" style={{
                        width: 40
                    }} source={require("./icon/logo.png")} />
                    <Text style={{
                        fontSize: 32,
                        color: "#650ab2",
                        fontWeight: "bold",
                        marginLeft: 8
                    }}>Fan:S</Text>
                </View>
                <TextInput
                    style={{
                        marginTop: 0,
                        width: 300,
                        borderRadius: 8,
                        borderBottomColor: "rgba(5, 26, 26, 0.2)",
                        borderBottomWidth: 1,
                        backgroundColor: "#FFFFFF"
                    }}
                    onChangeText={text => setEmail(text)}
                    placeholder="E-mail"
                    defaultvalue={email}
                />
                <TextInput
                    style={{
                        marginTop: 0,
                        width: 300,
                        marginTop: 8,
                        borderRadius: 8,
                        borderBottomColor: "rgba(5, 26, 26, 0.2)",
                        borderBottomWidth: 1,
                        backgroundColor: "#FFFFFF"
                    }}
                    onChangeText={text => setPassword(text)}
                    defaultvalue={password}
                    secureTextEntry={true}
                    placeholder="Password"
                />
                <TouchableOpacity onPress={logInButtonHandler} style={{
                    width: 300,
                    height: 35,
                    backgroundColor: "#650ab2",
                    borderRadius: 6,
                    marginTop: 20,

                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    justifyContent: "center"
                }}>
                    <Text style={{
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#ffffff"
                    }}>로그인</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => navigation.navigate("SignUp")} style={{
                    width: 300,
                    marginTop: 8,

                    display: "flex",
                    flexDirection: "column",
                    alignItems: "flex-end",
                    justifyContent: "center"
                }}>
                    <Text style={{
                        fontSize: 12,
                        color: "rgba(0, 0, 0, 0.6)",
                        textDecorationLine: "underline"
                    }}>아이디가 없으신가요?</Text>
                </TouchableOpacity>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    input: {
      height: 70,
      width: 300,
      margin: 12,
      borderWidth: 1,
      borderRadius: 25,
      backgroundColor: "#FFFFFF"
    },
});
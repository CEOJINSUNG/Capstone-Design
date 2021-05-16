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
    Alert
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
            navigation.navigate('Main', {token: response});
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <View style={{
                backgroundColor: "#9520FF",
                flex: 1,

                display: "flex",
                flexDirection: "column",
                alignItems: "center",
            }}>
                <Text style={{
                    fontSize: 64,
                    color: "#FFFFFF",
                    marginTop: 150,
                    fontWeight: "bold",
                }}>Fan:S</Text>
                <View style={{
                    marginTop: 80
                }}>
                    <TextInput
                        style={styles.input}
                        onChangeText={text => setEmail(text)}
                        placeholder="Please Type Your E-mail"
                        defaultvalue={email}
                    />
                    <TextInput
                        style={styles.input}
                        onChangeText={text => setPassword(text)}
                        defaultvalue={password}
                        secureTextEntry={true}
                        placeholder="Please Type Your Password"
                    />
                </View>
                <View
                    style={{
                        marginTop:50
                }}>
                    <Button
                        title="Log in"
                        color="#000000"
                        onPress={logInButtonHandler}
                    />
                </View>
                <View
                    style={{
                        marginTop:50
                }}>
                    <Button
                        title="Sign Up"
                        color="#777777"
                        onPress={() => navigation.navigate('SignUp')}
                    />
                </View>
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
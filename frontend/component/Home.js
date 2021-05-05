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
    const [email, onChangeEmail] = React.useState("");
    const [password, onChangePassword] = React.useState("");

    function logInButtonHandler(){
        fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: {email},
                password: {password}
            })
        })
        .then((response) => {
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
                        onChangeText={onChangeEmail}
                        placeholder="Please Type Your E-mail"
                        value={email}
                    />
                    <TextInput
                        style={styles.input}
                        onChangeText={onChangePassword}
                        value={password}
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
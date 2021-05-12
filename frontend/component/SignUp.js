import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    ScrollView,
    StatusBar,
    useColorScheme,
    TextInput,
    StyleSheet,
    Button
} from "react-native"

import { NavigationHelpersContext } from "@react-navigation/core";
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

export default function SignUp({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [name, onChangeName] = React.useState("");
    const [email, onChangeEmail] = React.useState("");
    const [password, onChangePassword] = React.useState("");
    const [nickname, onChangeNickname] = React.useState("");
    const [address, onChangeAddress] = React.useState("");
    const [phone_number, onChangePhoneNumber] = React.useState("");
    
    //TODO : backend port should be opened
    // TODO: make block chain address id will be added
    function signUpButtonHandler(){
        fetch('http://3.139.204.200:8080/auth/signup', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "email": email,
                "name": name,
                "password": password,
                "nickname": nickname,
                "address" : address,
                "phone_number" : phone_number,
                "blockchain_address" : "ABCDEF123456$",
                "user_type" : "f"
            })
        })
        .then((response) => {
            console.log(response.body);
            navigation.navigate('Home');
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView 
                style={{
                    contentInsetAdjustmentBehavior: "automatic",
                    backgroundColor: "#9520FF",
            }}>
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
                        marginTop: 20,
                        fontWeight: "bold",
                    }}>Fan:S</Text>
                    <View style={{
                        marginTop: 20
                    }}>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeName}
                            placeholder="Type Your Name"
                            value={name}
                        />
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeEmail}
                            placeholder="Type Your E-mail"
                            value={email}
                        />
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangePassword}
                            value={password}
                            secureTextEntry={true}
                            placeholder="Type Your Password"
                        />
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeNickname}
                            placeholder="Type Your Nickname"
                            value={nickname}
                        />
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeAddress}
                            placeholder="Type Your Address"
                            value={address}
                        />
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangePhoneNumber}
                            placeholder="Type Your Phone Number"
                            value={phone_number}
                        />
                    </View>
                    <View
                        style={{
                            marginTop:0
                    }}>
                        <Button
                            title="Submit"
                            color="#000000"
                            onPress={signUpButtonHandler}
                        />
                    </View>
                </View>
            </ScrollView>
        </SafeAreaView>
    );

    
}

const styles = StyleSheet.create({
    input: {
      height: 40,
      width: 300,
      margin: 12,
      borderWidth: 1,
      borderRadius: 25,
      backgroundColor: "#FFFFFF"
    },
});
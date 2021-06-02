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
    TouchableOpacity
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
                "Accept": 'application/json',
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
                    backgroundColor: "#FFFFFF",
            }}>
                <View style={{
                flex: 1,

                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                }}>
                    <Text style={{
                        fontSize: 64,
                        color: "#650ab2",
                        marginTop: 20,
                        fontWeight: "bold",
                    }}>Fan:S</Text>
                    <View style={{
                        marginTop: 20
                    }}>
                        <Text 
                            style={styles.text}>
                            Name
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeName}
                            placeholder="Type Your Name"
                            value={name}
                        />
                        <Text 
                            style={styles.text}>
                            E-mail
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeEmail}
                            placeholder="Type Your E-mail"
                            value={email}
                        />
                        <Text 
                            style={styles.text}>
                            Password
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangePassword}
                            value={password}
                            secureTextEntry={true}
                            placeholder="Type Your Password"
                        />
                        <Text 
                            style={styles.text}>
                            Nickname
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeNickname}
                            placeholder="Type Your Nickname"
                            value={nickname}
                        />
                        <Text 
                            style={styles.text}>
                            Address
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangeAddress}
                            placeholder="Type Your Address"
                            value={address}
                        />
                        <Text 
                            style={styles.text}>
                            Phone Number
                        </Text>
                        <TextInput
                            style={styles.input}
                            onChangeText={onChangePhoneNumber}
                            placeholder="Type Your Phone Number"
                            value={phone_number}
                        />
                    </View>
                <TouchableOpacity onPress={signUpButtonHandler} style={{
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
                    }}>회원가입</Text>
                </TouchableOpacity>
                </View>
            </ScrollView>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    input: {
        marginTop: 0,
        width: 300,
        borderRadius: 8,
        borderBottomColor: "rgba(5, 26, 26, 0.2)",
        borderBottomWidth: 1,
        backgroundColor: "#FFFFFF"
    },
    text: {
        marginTop: 10,
        width: 300,
        fontSize: 15,
        marginLeft: 5,
        color: "rgba(5, 26, 26, 0.6)"
    }
});
import React from "react"
import {
    View,
    Text,
    SafeAreaView,
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
                    marginTop: 50,
                    fontWeight: "bold",
                }}>Fan:S</Text>
                <View style={{
                    marginTop: 40
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
                </View>
                <View
                    style={{
                        marginTop:50
                }}>
                    <Button
                        title="Submit"
                        color="#000000"
                        onPress={() => navigation.navigate('Home')}
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
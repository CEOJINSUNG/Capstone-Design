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

import Main from './Main';

export default function Home({navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [text, onChangeText] = React.useState("");
    const [password, onChangePassword] = React.useState("");
  
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
                        onChangeText={onChangeText}
                        placeholder="Please Type Your ID"
                        value={text}
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
                        onPress={() => navigation.navigate('Main')}
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
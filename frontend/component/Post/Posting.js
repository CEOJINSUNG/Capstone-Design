import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StatusBar,
    useColorScheme,
    ScrollView,
    TextInput,
    StyleSheet,
    Image,
    Button,
    Alert
} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";
import * as ImagePicker from "react-native-image-picker"

import { NavigationHelpersContext } from "@react-navigation/core";
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

export default function Posting({route, navigation}) {
    const isDarkMode = useColorScheme() === 'dark';
    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");
    const [imageUri, setImageUri] = React.useState("");
    const [imageData, setImageData] = React.useState("");
    const {boardType} = route.params;

    async function pickImage(){
        var options = {
            title: 'Select Image',
            customButtons: [
              { 
                name: 'customOptionKey', 
                title: 'Choose file from Custom Option' 
              },
            ],
            storageOptions: {
              skipBackup: true,
              path: 'images',
            },
        };
        ImagePicker.launchImageLibrary(options, res => {
            console.log('Response = ', res);
      
            if (res.didCancel) {
                console.log('User cancelled image picker');
            } else if (res.error) {
                console.log('ImagePicker Error: ', res.error);
            } else if (res.customButton) {
                console.log('User tapped custom button: ', res.customButton);
              alert(res.customButton);
            } else {
                setImageUri(res.uri);
                setImageData('data:image/jpeg;base64,' + res.data);
                console.log(imageUri);
                console.log(imageData);
            }
        });
    }

    async function postButtonHandler(){
        var data = {
            "title" : title,
            "contents" : content,
            "images" : [imageData],
            "category" : boardType
        }
        await fetch('http://3.139.204.200:8080/post/save/1', {
            method: 'POST',
            credentials: true,
            headers: {
                "Accept": "application/json",
                'Content-type': 'application/json',
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            console.log(response);
            navigation.goBack();
        })
        .catch((error) => {
            console.log(error);
        });
    }


    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <ScrollView contentInsetAdjustmentBehavior="automatic"
                    style={{
                        marginLeft: "5%",
                        marginRight: "5%"
                    }}>
                <View style={{
                        display: "flex",
                        flexDirection: "row",
                        marginTop: 5,
                        marginBottom: 20
                    }}>
                    <Image style={{
                            alignSelf: "center",
                            width: 20,
                            height: 20,
                            marginRight: 15
                        }}
                        source={require("../icon/arrow.png")}/>
                    <View style={{
                        width: "80%"
                    }}>
                        <Text style={{
                            fontSize: 30,
                            fontWeight: "bold",
                            color: "#000000",
                        }}>
                            글 쓰기
                        </Text>
                    </View>
                    <TouchableOpacity onPress={postButtonHandler} style={{
                        width: 50,
                        height: 30,
                        backgroundColor: "#650AB2",
                        borderRadius: 8,
                        alignSelf: "center",
                        marginTop: 5,

                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center"
                    }}>
                        <Text style={{
                            fontSize: 12,
                            fontWeight: "bold",
                            color: "#ffffff"
                        }}>글쓰기</Text>
                    </TouchableOpacity>
                </View>
                <TextInput
                    style={styles.title}
                    onChangeText={text => setTitle(text)}
                    placeholder="Title"
                    defaultvalue={title}
                />
                <TextInput
                    style={styles.content}
                    multiline={true}
                    onChangeText={text => setContent(text)}
                    placeholder="Content"
                    defaultvalue={content}
                />
                <Image
                    source={imageUri ? {uri: imageUri} : null}
                    style={imageUri ? {width: 200, height: 200} : {width: 0, height: 0} }
                /> 
                <TouchableOpacity onPress={pickImage} style={styles.button}  >
                    <Text style={styles.buttonText}>Choose Image</Text>
                </TouchableOpacity> 
            </ScrollView>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    title: {
        height: 50,
        width: "90%",
        marginTop: 10,
        fontSize: 30,
        borderBottomColor: '#BBBBBB',
        borderBottomWidth: 1,
    },
    content: {
        width: "90%",
        marginTop: 10,
        fontSize: 20,
        flex: 1,
        justifyContent: 'flex-start',
        borderBottomColor: '#BBBBBB',
        borderBottomWidth: 1,
    },
    button: {
      width: 100,
      height: 30,
      backgroundColor: '#650AB2',
      alignItems: 'center',
      justifyContent: 'center',
      borderRadius: 4,
      marginTop:15    
    },
    buttonText: {
      textAlign: 'center',
      fontSize: 15,
      color: '#fff'
    }
  });
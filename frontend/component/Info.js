import React from "react"
import {
    View,
    Text,
    StatusBar,
    SafeAreaView,
    useColorScheme,
    Image,
    TouchableOpacity
} from "react-native"
import AntDesign from "react-native-vector-icons/AntDesign"

export default function Info({ navigation }) {
    const isDarkMode = useColorScheme() === 'dark';
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <View style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                marginTop: 16,
                marginLeft: 16,
            }}>
                <TouchableOpacity onPress={() => navigation.goBack()}>
                    <Image source={require("./icon/arrow.png")} style={{
                        width: 14,
                        height: 14,
                    }} />
                </TouchableOpacity>
                <Text style={{
                    fontSize: 16,
                    color: "#000000",
                    marginLeft: 8,
                    fontWeight: "bold"
                }}>설정</Text>
            </View>
            <View style={{
                marginTop: 12,
                marginLeft: 16,
            }}>
                <InfoList
                    icon={<Image source={require("./icon/megaphone.png")} />}
                    title="공지사항"
                />
                <InfoList 
                    icon={<Image source={require("./icon/exclamation.png")} />}
                    title="버전정보"
                />
                <InfoList 
                    icon={<Image source={require("./icon/lock.png")} />}
                    title="개인정보/보안"
                />
                <InfoList 
                    icon={<Image source={require("./icon/ring.png")} />}
                    title="알림"
                />
                <InfoList 
                    icon={<Image source={require("./icon/sun.png")} />}
                    title="화면"
                />
                <InfoList 
                    icon={<Image source={require("./icon/information.png")} />}
                    title="고객센터/도움말"
                />
                <InfoList 
                    icon={<Image source={require("./icon/more.png")} />}
                    title="기타"
                />
            </View>
        </SafeAreaView>
    )
}

const InfoList = ({ icon, title }) => {
    return (
        <View style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            marginTop: 16,
        }}>
            {icon}
            <Text style={{
                fontSize: 16,
                color: "#000000",
                marginLeft: 8,
                fontWeight: "bold"
            }}>{title}</Text>
        </View>
    )
}
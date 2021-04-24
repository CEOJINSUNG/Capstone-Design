import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StyleSheet,
    StatusBar,
    ScrollView,
    useColorScheme
} from "react-native"
import Ionicons from "react-native-vector-icons/Ionicons"
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons"

//Personal Style
const style = StyleSheet.create({
    row: {
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
    },
    column: {
        display: "flex",
        flexDirection: "column",
    },
    tenFont: {
        fontSize: 10,
        opacity: 0.6,
        color: "#000000",
        marginTop: 4
    }
})

//사용자 현재 상태
const Status = ({ current, standard }) => {
    return (
        <View style={[style.column, { alignItems: "flex-start", marginRight: 16, }]}>
            <Text style={{
                fontSize: 16,
                color: "#000000",
                fontWeight: "bold",
            }}>{current}</Text>
            <Text style={{
                fontSize: 10,
                color: "#000000",
                opacity: 0.6
            }}>{standard}</Text>
        </View>
    )
}

//NFT 로고 디자인
const NFTLogo = ({name, follower}) => {
    return (
        <View style={{
            width: 75,
            paddingTop: 12,
            paddingBottom: 8,
            backgroundColor: "#ffffff",
            elevation: 1.5,

            display: "flex",
            flexDirection: "column",
            alignItems: "center",
        }}>
            <View style={{
                width: 40,
                height: 40,
                borderRadius: 20,
                backgroundColor: "#000000",
            }} />
            <Text style={{
                fontSize: 10,
                color: "#000000",
                fontWeight: "bold",
                marginTop: 6,
            }}>{name}</Text>
            <Text style={{
                fontSize: 8,
                color: "#000000",
                opacity: 0.6, 
                marginTop: 4,
            }}>{follower} 팔로우</Text>
        </View>
    )
}

export default function Personal() {
    const isDarkMode = useColorScheme() === 'dark';
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <View style={{
                    width: "100%",
                    height: 225,
                    backgroundColor: "#650AB2",
                    marginTop: 40,
                }} />
                <View style={{
                    width: "90%",
                    padding: 16,
                    alignSelf: "center",
                    backgroundColor: "#ffffff",
                    elevation: 1.5,
                    marginTop: 30,
                    borderRadius: 6,
                }}>
                    <Text style={{
                        fontWeight: "bold",
                        fontSize: 12,
                        color: "#000000"
                    }}>김진성</Text>
                    <Text style={{
                        fontSize: 10,
                        color: "#000000",
                        opacity: 0.6,
                        marginTop: 8,
                    }}>언젠가 영국에 가서 손흥민 축구하는거 직관하고 싶은 사람</Text>
                    <View style={[style.row, { marginTop: 16 }]}>
                        <Status current="103K" standard="누적 후원 금액" />
                        <Status current="56.7" standard="예측 성공률(%)" />
                        <Status current="26" standard="현재 레벨(Lv)" />
                    </View>
                </View>
                <View style={[style.row, {
                    justifyContent: "space-between",
                    width: "90%",
                    padding: 16,
                    alignSelf: "center",
                    backgroundColor: "#ffffff",
                    elevation: 1.5,
                    marginTop: 12,
                    borderRadius: 6,
                }]}>
                    <View style={[style.column, { alignItems: "center", width: 60 }]}>
                        <Ionicons name="ios-wallet-outline" size={30} />
                        <Text style={style.tenFont}>FanS Wallet</Text>
                    </View>
                    <View style={[style.column, { alignItems: "center", width: 60 }]}>
                        <MaterialCommunityIcons name="notebook-outline" size={30} />
                        <Text style={style.tenFont}>내가 쓴 글</Text>
                    </View>
                    <View style={[style.column, { alignItems: "center", width: 60 }]}>
                        <MaterialCommunityIcons name="soccer-field" size={30} />
                        <Text style={style.tenFont}>예측 기록</Text>
                    </View>
                    <View style={[style.column, { alignItems: "center", width: 60 }]}>
                        <Ionicons name="settings-outline" size={30} />
                        <Text style={style.tenFont}>설정</Text>
                    </View>
                </View>
                <Text style={{
                    marginLeft: "5%",
                    fontSize: 16,
                    color: "#000000",
                    fontWeight: "bold",
                    marginTop: 24,
                }}>Fan'S List</Text>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    width: "90%",
                    alignSelf: "center",
                    marginTop: 16,
                }}>
                    <NFTLogo name="Tottenham" follower="110K" />
                </View>
                <Text style={{
                    alignSelf: "center",
                    fontSize: 8,
                    color: "#000000",
                    opacity: 0.6,
                    marginTop: 40,
                    marginBottom: 20,
                }}>Licensed by Kim Jin Sung</Text>
            </ScrollView>
        </SafeAreaView>
    )
}
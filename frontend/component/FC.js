import React from "react"
import {
    View,
    Text,
    SafeAreaView,
    StyleSheet,
    StatusBar,
    ScrollView,
    useColorScheme,
    TouchableWithoutFeedback,
    Image
} from "react-native"
import Swiper from "react-native-swiper"
import MaterialIcons from "react-native-vector-icons/MaterialIcons"

//Football Club Style
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

//이주의 예측 리스트
const PredictForm = ({num, name, title, vote}) => {
    return (
        <View style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "space-between",
            alignSelf: "center",
            width: "90%",
            marginBottom: 4,
        }}>
            <View style={style.row}>
                <Text style={{
                    fontSize: 14,
                    color: "#650AB2",
                    fontWeight: "bold",
                    marginRight: 8,
                }}>{num}</Text>
                <Text style={{
                    fontSize: 12,
                    fontWeight: "bold",
                    color: "#000000",
                }}>{name}</Text>
            </View>
            <Text style={{
                fontSize: 10,
                color: "#000000",
            }}>{title}</Text>
            <Text>
                <Text style={{
                    fontSize: 12,
                    fontWeight: "bold",
                    color: "#650AB2",
                }}>{vote} </Text>
                <Text style={{
                    fontSize: 8,
                    opacity: 0.34,
                    color: "#000000",
                }}>vote</Text>
            </Text>
        </View>
    )
}

//구단 카드 디자인
const ClubDesign = ({name, rank, score}) => {
    return (
        <View style={{
            width: 80,
            paddingTop: 12,
            paddingBottom: 16,
            marginLeft: 10,
            marginRight: 10,
        }}>
            <View style={{
                width: 40,
                height: 40,
                borderRadius: 20,
                backgroundColor: "#000000",
                marginBottom: 4,
            }} />
            <Text style={{
                fontSize: 10,
                fontWeight: "bold",
                color: "#000000",
                marginBottom: 8,
            }}>{name}</Text>
            <Text style={{
                fontSize: 10,
                fontWeight: "bold",
                color: "#000000",
            }}>
                <Text>{rank}위 </Text>
                <Text>{score}점</Text>
            </Text>
        </View>
    )
}

//이주의 뉴스
const NewsForm = ({ num, title }) => {
    return (
        <View style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            alignSelf: "center",
            marginBottom: 4,
        }}>
            <Text style={{
                fontSize: 12,
                color: "#650AB2",
                fontWeight: "bold",
                marginRight: 4,
            }}>{num}</Text>
            <Text style={{
                fontSize: 10,
                color: "#000000",
            }}>{title}</Text>
        </View>
    )
}

export default function FC() {
    const isDarkMode = useColorScheme() === 'dark';

    const predictData = [
        {
            num: 1,
            name: "JS Director",
            title: "무리뉴의 수비축구는 계속..",
            vote: 123,
        },
        {
            num: 2,
            name: "JS Director",
            title: "무리뉴의 수비축구는 계속..",
            vote: 123,
        },
        {
            num: 3,
            name: "JS Director",
            title: "무리뉴의 수비축구는 계속..",
            vote: 123,
        },
        {
            num: 4,
            name: "JS Director",
            title: "무리뉴의 수비축구는 계속..",
            vote: 123,
        },
        {
            num: 5,
            name: "JS Director",
            title: "무리뉴의 수비축구는 계속..",
            vote: 123,
        },
    ]
    const leagueRank = [
        {
            name: "Tottenham",
            rank: 1,
            score: 72,
        },
        {
            name: "Tottenham",
            rank: 2,
            score: 68,
        },
        {
            name: "Tottenham",
            rank: 3,
            score: 60,
        },
    ]
    const newsData = [
        {
            num: 1,
            title: "1년전보다 손TOP 위력 반감.. 조력자 이탈하…",
        },
        {
            num: 2,
            title: "1년전보다 손TOP 위력 반감.. 조력자 이탈하…",
        },
        {
            num: 3,
            title: "1년전보다 손TOP 위력 반감.. 조력자 이탈하…",
        },
        {
            num: 4,
            title: "1년전보다 손TOP 위력 반감.. 조력자 이탈하…",
        },
        {
            num: 5,
            title: "1년전보다 손TOP 위력 반감.. 조력자 이탈하…",
        },
    ]
    return (
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <Swiper
                    height={225}
                    dotColor="#ffffff"
                    activeDotColor="#650AB2"
                >
                    <View style={{
                        width: "100%",
                        height: 225,
                        backgroundColor: "#ffffff",
                        position: "absolute",
                        zIndex: 0,
                    }}>
                        <View style={{
                            backgroundColor: "#000000",
                            width: "100%",
                            height: 225,
                            opacity: 0.4,
                            zIndex: 1,
                        }} />
                        <View style={{
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "flex-start",
                            zIndex: 2,
                            left: "5%", 
                            bottom: 90,
                        }}>
                            <Text style={{
                                fontSize: 16,
                                fontWeight: "bold",
                                color: "#ffffff",
                            }}>이주의 선수를 예측하고</Text>
                            <Text style={{
                                fontSize: 20,
                                fontWeight: "bold",
                                color: "#650AB2"
                            }}>레벨을 올리자</Text>
                        </View>
                    </View>
                    <View style={{
                        flex: 1,
                        backgroundColor: "#5cc27b"
                    }}></View>
                </Swiper>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",
                    width: "90%",
                    alignSelf: "center",
                    marginTop: 24,
                    marginBottom: 8,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                    }}>
                        <Text style={{color: "#FF0000"}}>이주의 </Text>
                        <Text style={{color: "#000000"}}>예측</Text>
                    </Text>
                    <Text style={{
                        fontSize: 10,
                        color: "#000000",
                        opacity: 0.4,
                        textDecorationLine: "underline"
                    }}>투표하러 가기</Text>
                </View>
                {predictData.map(item => (<PredictForm num={item.num} name={item.name} title={item.title} vote={item.vote} />))}
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",
                    width: "90%",
                    alignSelf: "center",
                    marginTop: 16,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000",
                    }}>리그순위</Text>
                    <TouchableWithoutFeedback >
                        <View style={{
                            display: "flex",
                            flexDirection: "row",
                            alignItems: "center",
                        }}>
                            <Text style={{
                                fontSize: 12,
                                color: "#000000",
                                opacity: 0.6,
                            }}>EPL</Text>
                            <MaterialIcons name="arrow-drop-down" size={16} color="rgba(0, 0, 0, 0.6)" />
                        </View>
                    </TouchableWithoutFeedback>
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    justifyContent: "center",
                    width: "90%",
                    alignSelf: "center",
                    marginLeft: "5%",
                    marginTop: 20,
                }}>
                    {leagueRank.map(item => (<ClubDesign name={item.name} rank={item.rank} score={item.score} />))}
                </View>
                <View style={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "flex-end",
                    justifyContent: "space-between",
                    width: "90%",
                    alignSelf: "center",
                    marginTop: 16,
                }}>
                    <Text style={{
                        fontSize: 14,
                        fontWeight: "bold",
                        color: "#000000",
                    }}>뉴스</Text>
                    <TouchableWithoutFeedback >
                        <Text style={{
                            fontSize: 12,
                            color: "#000000",
                            opacity: 0.6,
                            textDecorationLine: "underline",
                        }}>더보기</Text>
                    </TouchableWithoutFeedback>
                </View>
                <View style={[style.row, { marginTop: 12, width: "90%", marginLeft: "5%", marginBottom: 40, }]}>
                    <Image
                        source={require("./icon/news.png")}
                        style={{
                            width: 120,
                            height: 88,
                            backgroundColor: "#000000"
                        }}
                    />
                    <View style={{
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "flex-start",
                        marginLeft: 4,
                    }}>
                        {newsData.map(item => (
                            <NewsForm num={item.num} title={item.title} />
                        ))}
                    </View>
                </View>
            </ScrollView>
        </SafeAreaView>
    )
}
import React from "react"
import { 
    View,
    Text,
    Image,
    ScrollView, 
    SafeAreaView,
    TextInput} from "react-native"
import { TouchableOpacity } from "react-native-gesture-handler";

//TODO : imageDir to image from DB
const PostForm = ({title, content, imageDir, like, comments}) => {
    return (
        <TouchableOpacity style={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
            justifyContent: "space-between",
            alignSelf: "center",
            width: "90%",
            height: 60,
            marginTop: 10,
        }}>
            <Image style={{
                    width: "20%",
                    height: "100%",
                }} 
                source={require("../icon/news.png")} />
            <View style={{
                    width: "65%"
                }}>
                <Text style={{
                    fontSize: 12,
                    fontWeight: "bold",
                    color: "#000000",
                }}>{title}</Text>
                <Text style={{
                    fontSize: 10,
                    color: "#000000",
                    marginTop: 4,
                }}>{content}</Text>
            </View>
            <View style={{
                    width: "10%",
                    height: 70,
                }}>
                <View style={{
                    flex: 1,
                    justifyContent: 'flex-end',
                }}>
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        marginBottom: 3,
                    }}>
                        <Text style={{
                            fontSize: 7,
                            color: "#000000"
                        }}>{like}likes {comments}com</Text>
                    </View>
                </View>
            </View>
        </TouchableOpacity>
    )
}

export default function FreeBoard({token, navigation}) {   
    const [search, setSearch] = React.useState("");
   
    const postData = [
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
        {
            title: "이번에 메시 연봉 공개 뭐냐",
            content: "메시 4년 계약 4000억원이고 그러면 주급이 ㄹㅇ 25억인데 개부럽다 메시..",
            imageDir: "../icon/news.png",
            like: 142,
            comments: 54,
        },
    ]
    return(
        <SafeAreaView style={{ backgroundColor: "#ffffff", flex: 1 }}>
            <ScrollView contentInsetAdjustmentBehavior="automatic">
                <View style={{
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "space-between",
                        marginTop: 20,
                        marginBottom: 20,
                        marginLeft: "5%"
                    }}>
                    <View style={{
                        width: "25%"
                    }}>
                        <Text style={{
                            fontSize: 30,
                            fontWeight: "bold",
                            color: "#000000",
                        }}>
                            자유게시판
                        </Text>
                    </View>
                    <TouchableOpacity onPress={() => navigation.navigate("Posting", { boardType: "free", "token": token })} style={{
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
                    <View style={{
                        display: "flex",
                        flexDirection: "row",
                        alignItems: "center",
                        width: "40%"
                    }}>
                        <TextInput
                            style={{
                                height: 40,
                                width: "70%",
                                borderWidth: 1,
                                borderRadius: 5,
                                fontSize: 10
                            }}
                            onChangeText={text => setSearch(text)}
                            defaultvalue={search}
                            placeholder="제목, 내용 검색"
                        
                        />
                        <Image style={{
                                marginLeft: 10,
                                marginTop: 15,
                                width: "10%",
                                height: "20%",
                            }}
                            source={require("../icon/arrow.png")}/>
                    </View>
                </View>
                {postData.map(item => (<PostForm title={item.title} content={item.content} imageDir={item.imageDir} like={item.like} comments={item.comments} />))}

            </ScrollView>
        </SafeAreaView>
       
    );
}      
                
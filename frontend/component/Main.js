import React, { useState, useEffect, useLayoutEffect } from "react";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from "react-native-vector-icons/Ionicons";
import FC from './FC';
import Personal from './Personal';
import Community from './Community';

const Tab = createBottomTabNavigator();

export default function Main({ route, navigation }) {
    const { token } = route.params;
    const [img, setImage] = useState("")
    const [tran, setTran] = useState("")
    const [count, setCount] = useState(0)

    useEffect(() => {
        setTimeout(() => {
            setCount(count + 1)
        }, 3000)
        fetch('http://3.139.204.200:8080/user/histories', {
            method: 'GET',
            headers: {
                "Accept": "application/json",
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': token
            }
        })
            .then(res => res.json())
            .then(response => {
                setImage(response[5].imageUrl)
                setTran(response[5].ehterUrl)
            })
            .catch((error) => {
            });
    }, [count])
    return (
        <Tab.Navigator
            screenOptions={({ route }) => ({
                tabBarLabel: ({ focused }) => {
                    return null
                },
                tabBarIcon: ({ focused, color, size }) => {
                    let iconName;

                    if (route.name === 'Community') {
                        iconName = focused
                            ? 'chatbox-ellipses'
                            : 'chatbox-ellipses-outline';
                    } else if (route.name === 'FC') {
                        iconName = focused ? 'football' : 'football-outline';
                    } else if (route.name === "Personal") {
                        iconName = focused ? "person-circle" : "person-circle-outline"
                    }

                    return <Ionicons name={iconName} size={24} color={color} />;
                },
            })
            }
            tabBarOptions={{
                activeTintColor: '#650ab2',
                inactiveTintColor: '#000000',
            }}
        >
            <Tab.Screen name="Community" children={() => <Community token={token} navigation={navigation} />} />
            <Tab.Screen name="FC" component={FC} />
            <Tab.Screen name="Personal" children={() => <Personal token={token} navigation={navigation} img={img} url={tran} />} />
        </Tab.Navigator>
    );
}
import React from "react"

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import FC from './FC';
import Personal from './Personal';
import Splash from './Splash';
import Community from './Community';

const Tab = createBottomTabNavigator();

export default function Main({navigation}) {
    return (
        <Tab.Navigator>
            <Tab.Screen name="Community" component={Community} />
            <Tab.Screen name="Splash" component={Splash} />
            <Tab.Screen name="Personal" component={Personal} />
            <Tab.Screen name="FC" component={FC} />
        </Tab.Navigator>
    );
}
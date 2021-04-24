import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import FC from './component/FC';
import Personal from './component/Personal';

const Tab = createBottomTabNavigator()

const App = () => {

  return (
    <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="Personal" component={Personal} />
        <Tab.Screen name="FC" component={FC} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

export default App;

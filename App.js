/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  Dimensions
} from 'react-native';


import  OSRMMapView from './OSRMMapView.js';


const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});


/**
    GeoPoint startPoint = new GeoPoint(31.3225178,-89.3174118);
        GeoPoint endPoint=new GeoPoint(31.3339492,-89.3376635);

        **/
        
export default class App extends Component<{}> {
  render() {
    return (
      <View style={{width:Dimensions.get('window').width,height:Dimensions.get('window').height/2}}>
      

  <OSRMMapView style={styles.container}
        location={{latitude:31.3225178,longitude:-89.3174118}}
        zoomLevel={13}
        route={[{latitude:31.3225178,longitude:-89.3174118},{latitude:31.3339492,longitude:-89.3376635}]}
        ></OSRMMapView>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex:1,
    flexDirection:'row',
    width:Dimensions.get('window').width,
    height:Dimensions.get('window').height/2,
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

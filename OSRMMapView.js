import PropTypes from 'prop-types';

var {requireNativeComponent,View}=require('react-native');

var mapView={
	name:'OSRMMapView',
	propTypes:{
		location:PropTypes.object,
		zoomLevel:PropTypes.number,
		route:PropTypes.array,
		...View.propTypes // include the default view properties
	}
};

module.exports=requireNativeComponent('OSRMAndroidMapView',mapView);

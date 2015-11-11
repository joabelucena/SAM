require.config({
    baseUrl: 'js',
    paths: {
        jquery: 'lib/jquery.min'
    }
});

require(["jquery", "app/genium"], function($, genium) {
    $(document).ready(function() {
		genium.init();
    });
});
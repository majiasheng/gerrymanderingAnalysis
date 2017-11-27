var dataChart1 = null;
gapi.analytics.ready(function() {

  /**
   * Create the first DataChart for top countries over the past 30 days.
   * It will be rendered inside an element with the id "chart-1-container".
   */
  dataChart1 = new gapi.analytics.googleCharts.DataChart({
    query: {
      metrics: 'ga:sessions',
      dimensions: 'ga:country',
      'start-date': '30daysAgo',
      'end-date': 'yesterday',
      'max-results': 6,
      sort: '-ga:sessions'
    },
    chart: {
      container: 'chart-1-container',
      type: 'PIE',
      options: {
        width: '100%',
        pieHole: 4/9
      }
    }
  });

});

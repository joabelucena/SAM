Ext.define('Sam.view.ViewportHeader', {
	extend: 'Ext.toolbar.Toolbar',
	alias: 'widget.viewportheader',
	
	height: 60,
	ui: 'footer',
	items: [
		{
			xtype: 'label'
		},{
			xtype: 'tbfill',
			html: "<img border='0' width='190px' height='37px' src='./resources/resources/img/logo-vlt-emtu.png' />"
		},{
			xtype: 'tbfill',
			html: '<center><span style="color:#444D50;font-size:20px;font-weight:bold;">SAM - Sistema de Apoio a Manutenção</span></center>'
		},{
			xtype: 'tbfill',
			html: "<img border='0' width='108px' height='30px' align='right' src='./resources/resources/img/logo-ttrans.png' />"
		},{
			xtype: 'tbseparator',	
		},{
			xtype: 'button',
			itemId: 'logout',
			width: '55px',
			handler: function() {
				window.location = 'logout';
		    },
			text: 'Sair'
		}
	]
});
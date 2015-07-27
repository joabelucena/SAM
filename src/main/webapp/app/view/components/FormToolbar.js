Ext.define('Sam.view.components.FormToolbar', {
	extend : 'Ext.toolbar.Toolbar',	
	alias : 'widget.formtoolbar',

    dock: 'bottom',
    
    items: [{
    	xtype: 'tbfill'
    },{
        xtype:'button',
    	itemId:'btnSubmit',
    	text:'Confirma',
        tooltip:'Confirmar Operação',
        cls:'x-btn-default-small',
        iconCls: 'tick-button'
    },{
        xtype:'button',
    	itemId:'btnDiscard',
    	text:'Cancela',
        tooltip:'Cancelar Operação',
        cls:'x-btn-default-small',
        iconCls: 'tick-button',
        handler: function(button){
        	Ext.getCmp('viewportpanel').getActiveTab().close();
        }
    }]
});
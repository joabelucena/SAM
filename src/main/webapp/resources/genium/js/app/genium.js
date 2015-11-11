define(['jquery'], function($) {
	'use strict';

	var Genium = {
		sequencia: [],
		copia: [],
		rodada: 0,
		ativo: true,
		modo: 'normal',
	
		init: function() {
			var that = this;
			$('[data-acao=start]').on('click', function() {
				that.iniciaJogo();
			});
		},

		iniciaJogo: function() {
			this.sequencia = [];
			this.copia = [];
			this.rodada = 0;
			this.ativo = true;
			$('p[data-acao="lose"]').hide();
			this.novaRodada();
		},

		// adiciona uma nova cor a sequencia
		novaRodada: function() {
			$('[rodada]').text(++this.rodada);
			this.sequencia.push(this.numeroAleatorio());
			this.copia = this.sequencia.slice(0);
			this.animacao(this.sequencia);
		},

		// Verifica se a sequencia está correta e verifica se perdeu
		armazenaClick: function(e) {
			var respostaCorreta = this.copia.shift();
			var respostaAtual = $(e.target).data('botao');
			this.ativo = (respostaCorreta === respostaAtual);
			this.Perdeu();
		},

		Perdeu: function() {
			
			if (this.copia.length === 0 && this.ativo) {
				this.desativaJogo();
				this.novaRodada();

			} else if (!this.ativo) { // perdeu
				this.desativaJogo();
				this.finalDoJogo();
			}
		},

		finalDoJogo: function() {
			$('p[data-acao=lose]').show();
			$($('[data-rodada]').get(0)).text('0');
		},

		ativaJogo: function() {
			var that = this;
			$('.genium')
				.on('click', '[data-botao]', function(e) {
					that.armazenaClick(e);
				})

				.on('mousedown', '[data-botao]', function(){
					$(this).addClass('ativo');
					that.tocaSom($(this).data('botao'));
				})

				.on('mouseup', '[data-botao]', function(){
					$(this).removeClass('ativo');
				});

			$('[data-botao]').addClass('hoverable');
		},

		desativaJogo: function() {
			if (this.modo !== 'free-board') {
				$('.genium')
					.off('click', '[data-botao]')
					.off('mousedown', '[data-botao]')
					.off('mouseup', '[data-botao]');

				$('[data-botao]').removeClass('hoverable');
			}
		},

		animacao: function(sequencia) {
			var i = 0;
			var that = this;
			var interval = setInterval(function() {
				that.tocaSom(sequencia[i]);
				that.acendeLuz(sequencia[i]);

				i++;
				if (i >= sequencia.length) {
					clearInterval(interval);
					that.ativaJogo();
				}
			}, 600);
		},

		acendeLuz: function(botao) {
			if (this.modo !== 'sound-only') {
				var $botao = $('[data-botao=' + botao + ']').addClass('aceso');
				window.setTimeout(function() {
					$botao.removeClass('aceso');
				}, 300);
			}

		},


		tocaSom: function(botao) {
			if (this.modo !== 'light-only') {
				var audio = $('<audio autoplay></audio>');
				audio.append('<source src="sounds/' + botao + '.ogg" type="audio/ogg" />');
				audio.append('<source src="sounds/' + botao + '.mp3" type="audio/mp3" />');
				$('[data-acao=sound]').html(audio);
			}
		},

		numeroAleatorio: function() {
			// entre 1 e 4
			return Math.floor((Math.random()*4)+1);
		}
	};

	return Genium;
});

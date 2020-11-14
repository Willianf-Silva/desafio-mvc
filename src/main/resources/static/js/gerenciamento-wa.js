$(function () {
	$('[rel="tooltip"]').tooltip();
});

/**
 * Ativando o modal quando selecionado a opção de excluir funcionário
 */
$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
	var funcionarioNome = button.data('nome');
	var funcionarioId = button.data('id');
	var funcionarioMatricula = button.data('matricula');
		
	var modal = $(this);
	var form = modal.find('form')
	var action = form.data('url-base')
	
	if(!action.endsWith('/')){
		action += '/';
	}
	
	form.attr('action', action + funcionarioId)
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o funcionário <strong>' + 
		funcionarioNome + '</strong> com matrícula <strong>' + 
		funcionarioMatricula + '</strong>')
  })

  /**
 * Ativando o modal quando selecionado a opção de excluir vaga
 */
$('#confirmacaoExclusaoModalVaga').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
	var vagaProjeto = button.data('projeto');
	var vagaId = button.data('id');
	var vagaCodigo = button.data('codigovaga');
		
	var modal = $(this);
	var form = modal.find('form')
	var action = form.data('url-base')
	
	if(!action.endsWith('/')){
		action += '/';
	}
	
	form.attr('action', action + vagaId)
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a vaga para o projeto <strong>' + 
		vagaProjeto + '</strong> com código <strong>' + 
		vagaCodigo + '</strong>')
  })
function showCoverPicture(){

	let fileName = document.querySelector(".album-cover-picture").files[0].size;
	let filePath = URL.createObjectURL(document.querySelector(".album-cover-picture").files[0]);
	let coverBlock = document.querySelector(".album-cover");
	coverBlock.style = "background-image: url("+filePath+")";
	let addedTracksContainer = document.querySelector(".added-tracks-block");
	console.log(fileName);
	if (addedTracksContainer.children.length > 0) {
		for (var i = 0; i < addedTracksContainer.children.length; i++) {
			addedTracksContainer.children[i].querySelector(".added-track-cover").style.backgroundImage = "url("+filePath+")";
		}
	}	
}


function togglePriceBlock(){

	let isPaidPolicySelected = document.querySelector(".select-access-policy").options.selectedIndex;
	let priceBlock = document.querySelector(".album-price-input");
	let priceBlockLabel = document.querySelector(".album-price-input-label");

	if (isPaidPolicySelected == 1) {

		priceBlock.style = "opacity: 0";
		priceBlockLabel.style = "opacity: 0";

	}else {
		priceBlock.style = "opacity: 1";
		priceBlockLabel.style = "opacity: 1";
	}
}

function addTagToSelectedGenres(){

	let tagsContainer = document.querySelector(".selected-genres");
	let selectedIndex = document.querySelector(".select-album-genres").options.selectedIndex;
	let selectedGenre = document.querySelector(".select-album-genres").options[selectedIndex].value;
	for(i=0; i < tagsContainer.children.length; i++){

		if (tagsContainer.children.length == 0) {
			continue;
		}else {
			if (tagsContainer.children[i].textContent == selectedGenre) {
				return;
			}
		}
	}

	let tag = document.createElement("figure");
	let removeTagButton = document.createElement("a");
	removeTagButton.className = "genre-tag-remove-tag-button";
	removeTagButton.setAttribute("type", "button");
	tag.className = "genre-tag";
	tag.style = "display: flex";
	tag.innerHTML = selectedGenre;
	tag.appendChild(removeTagButton);
	tagsContainer.appendChild(tag);
	console.log(tag.textContent);

}


function createNewAlbumTrack(){
	let albumCoverInput = document.querySelector(".album-cover-picture").files[0];
	let addedTracksContainer = document.querySelector(".added-tracks-block");

	if (addedTracksContainer.children.length == 20) {	
		return;
	}else{
		let numberOfTrackInContainer = addedTracksContainer.children.length;

		let track = document.createElement("figure");
		let trackCover = document.createElement("figure");
		let trackInputLabel = document.createElement("label");
		let trackInput = document.createElement("input");
		let trackEditableName = document.createElement("p");
		let trackPrice = document.createElement("p");
		trackInputLabel.className = "added-track-file-input-label";
		trackInputLabel.textContent = "Select File";
		trackInputLabel.setAttribute("for", "fileInput"+numberOfTrackInContainer+"");
		trackPrice.className = "track-price";
		trackPrice.textContent = "0.00";
		trackPrice.setAttribute("onkeypress", "checkIfValid(event)");
		trackPrice.setAttribute("contenteditable", "True");
		trackEditableName.setAttribute("contenteditable","true");
		trackEditableName.textContent = "Write the title of track";
		trackEditableName.className = "track-name";
		trackInput.setAttribute("type", "file");
		trackInput.className = "added-track-file-input";
		trackInput.setAttribute("accept", ".mp3");
		trackInput.setAttribute("id", "fileInput"+numberOfTrackInContainer+"");
		trackCover.className = "added-track-cover";
		track.className = "added-track";
		track.appendChild(trackCover);
		track.appendChild(trackEditableName);
		track.appendChild(trackInputLabel);
		track.appendChild(trackInput);
		track.appendChild(trackPrice);
		
		if (albumCoverInput == undefined) {
			console.log("Now empty");
		}else {
			let albumCoverInput = document.querySelector(".album-cover-picture").files[0];
			let filePath = URL.createObjectURL(albumCoverInput);
			trackCover.style = "background-image: url("+filePath+")";

		}

		addedTracksContainer.appendChild(track);

	}
}

window.onload = function(){

	let genres = document.querySelector(".selected-genres");
	genres.onclick = function(event){
		let targetevent = event.target;
		if (targetevent.className == "genre-tag-remove-tag-button") {
			targetevent.parentNode.remove();
		}
	}

	let addedTrackInputs = document.querySelectorAll(".added-track-file-input");
	let addedTracksContainer = document.querySelector(".added-tracks-block");
	addedTracksContainer.addEventListener("DOMNodeInserted", function(){
		 let fileInput = event.target.querySelectorAll(".added-track-file-input");
		 for (let i = 0; i < fileInput.length; i++) {
		 	fileInput[i].addEventListener("change",
				function(event){
					if (event.target.files[0] != undefined) {
						if ((event.target.files[0].size)/(1024*1024) > 20) {
							alert("You can't upload track of size more than 20 mbs");
							event.target.files[0] = null;
							event.target.previousSibling.textContent = "Select File";

						}else{
							event.target.previousSibling.textContent = event.target.files[0].name;
							console.log(event.target.files[0].name);
						}
					}
				}, false);
		 }
	}, false);


}

	
function checkIfValid(e){
	if (!e.key.match(/[0-9\.]/i)) {
		e.preventDefault();
	}
}





	

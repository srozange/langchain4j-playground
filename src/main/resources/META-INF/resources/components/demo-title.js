import {LitElement, html, css} from 'lit';

export class DemoTitle extends LitElement {

    static styles = css`
      h1 {
        font-family: "Red Hat Mono", monospace;
        font-size: 60px;
        font-style: normal;
        font-variant: normal;
        font-weight: 700;
        line-height: 26.4px;
        color: var(--main-highlight-text-color);
      }

      .title {
        text-align: center;
        padding: 1em;
        background: var(--main-bg-color);
      }
      
      .explanation {
        margin-left: auto;
        margin-right: auto;
        width: 50%;
        text-align: justify;
        font-size: 20px;
      }
      
      .explanation img {
        max-width: 60%;
        display: block;
        float:left;
        margin-right: 2em;
        margin-top: 1em;
      }
    `

    render() {
        return html`
            <div class="title">
                <h1>Hello!</h1>
            </div>
            
            <!--div class="explanation">
                Cette démonstration montre comment construire un chatbot alimenté par un LLM local et la génération augmentée par récupération.
                La description des différents comptes est ingérée dans une base de données Qdrant, et les informations pertinentes
                sont envoyées au LLM avant de répondre à l'utilisateur.
            </div>
            
            <div class="explanation">
                <ol>
                    <li>L'utilisateur envoie une question à l'application.</li>
                    <li>L'application recherche des données pertinentes dans le store Qdrant.</li>
                    <li>Les données pertinentes sont récupérées et ajoutées à la question de l'utilisateur.</li>
                    <li>La question enrichie est envoyée au modèle LLM local.</li>
                    <li>La réponse est reçue et renvoyée à l'utilisateur.</li>
                </ol>
            </div -->
        `
    }


}

customElements.define('demo-title', DemoTitle);
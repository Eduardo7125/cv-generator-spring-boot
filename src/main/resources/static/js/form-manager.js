document.addEventListener('DOMContentLoaded', function () {

    const themeToggle = document.getElementById('theme-toggle');
    const htmlEl = document.documentElement;
    const setTheme = (theme) => {
        htmlEl.setAttribute('data-theme', theme);
        localStorage.setItem('theme', theme);
        themeToggle.checked = theme === 'dark';
    };
    const savedTheme = localStorage.getItem('theme') || (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');
    setTheme(savedTheme);
    themeToggle.addEventListener('change', () => setTheme(themeToggle.checked ? 'dark' : 'light'));

    const profileImageInput = document.getElementById('profileImage');
    const imagePreview = document.getElementById('image-preview');

    if (profileImageInput && imagePreview) {
        profileImageInput.addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    imagePreview.style.backgroundImage = `url('${e.target.result}')`;
                    imagePreview.classList.add('has-image');
                }
                reader.readAsDataURL(file);
            }
        });
    }

    const addSectionBlock = (containerId, templateId, indexVarName) => {
        const container = document.getElementById(containerId);
        const template = document.getElementById(templateId);
        if (!container || !template) return;

        const clone = template.content.cloneNode(true);
        let index = parseInt(container.dataset[indexVarName] || '0');

        clone.querySelectorAll('[data-name]').forEach(input => {
            const nameTemplate = input.getAttribute('data-name');
            input.setAttribute('name', nameTemplate.replace('__INDEX__', index));
            input.removeAttribute('data-name');
        });

        const newBlock = document.createElement('div');
        newBlock.className = 'dynamic-section-block';

        const removeButton = document.createElement('button');
        removeButton.type = 'button';
        removeButton.className = 'remove-btn';
        removeButton.innerHTML = '&times;';
        removeButton.title = 'Eliminar esta sección';
        removeButton.onclick = function() {
            newBlock.style.transition = 'opacity 0.3s ease, transform 0.3s ease, margin 0.3s ease, padding 0.3s ease, height 0.3s ease';
            newBlock.style.opacity = '0';
            newBlock.style.transform = 'scale(0.95)';
            newBlock.style.margin = '0';
            newBlock.style.padding = '0';
            newBlock.style.height = '0px';
            newBlock.style.border = 'none';

            setTimeout(() => newBlock.remove(), 300);
        };

        newBlock.appendChild(clone);
        newBlock.appendChild(removeButton);
        container.appendChild(newBlock);
        container.dataset[indexVarName] = index + 1;
    };

    const setupPlaceholder = (placeholderId, containerId, templateId, indexVarName) => {
        const placeholder = document.getElementById(placeholderId);
        if (placeholder) {
            placeholder.addEventListener('click', () => addSectionBlock(containerId, templateId, indexVarName));
            addSectionBlock(containerId, templateId, indexVarName);
        }
    };

    setupPlaceholder('add-experience', 'experiences-container', 'experience-template', 'experienceIndex');
    setupPlaceholder('add-education', 'educations-container', 'education-template', 'educationIndex');
    setupPlaceholder('add-certification', 'certifications-container', 'certification-template', 'certificationIndex');
    setupPlaceholder('add-project', 'projects-container', 'project-template', 'projectIndex');
    setupPlaceholder('add-language', 'languages-container', 'language-template', 'languageIndex');
});